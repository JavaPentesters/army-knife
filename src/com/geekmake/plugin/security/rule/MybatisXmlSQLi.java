package com.geekmake.plugin.security.rule;

import static com.geekmake.plugin.utils.SQLi.*;

import java.util.*;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import com.geekmake.plugin.utils.SQLi;
import com.geekmake.plugin.utils.Str;
import com.intellij.codeInspection.*;
import com.intellij.lang.ASTFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.xml.*;
import com.intellij.xml.util.XmlUtil;

/**
 * 针对mybatis xml文件中存在${var}存在可能被SQL注入场景
 * 
 * @author pez1420@gmail.com
 * @version $Id: MybatisXmlSQLi.java v 0.1 2020/10/14 11:04 上午 pez1420 Exp $$
 */
public class MybatisXmlSQLi extends AbstractBaseJavaLocalInspectionTool {

    public static final String           MESSAGE                = "17winSec: Mybatis XML SQL注入漏洞";
    private static final String          QUICK_FIX_NAME         = "!Fix: 替换为 #{hashtag}";

    protected static final Set<String>   ignoreVarName          = new HashSet<>(
        Arrays.asList("orderByClause", "pageStart", "pageSize", "criterion.condition"));
    private final MybatisXmlSQLiQuickFix mybatisXmlSQLiQuickFix = new MybatisXmlSQLiQuickFix();

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlText(XmlText text) {
                if (text.getParentTag() != null
                    && ("sql".equals(text.getParentTag().getName())
                        || "mapper".equals(text.getParentTag().getName()))) {
                    return;
                }

                XmlDocument document = XmlUtil.getContainingFile(text).getDocument();
                if (document == null) {
                    return;
                }
                String dtd = XmlUtil.getDtdUri(document);
                if (dtd == null || !(dtd.contains("mybatis.org") && dtd.contains("mapper.dtd"))) {
                    return;
                }

                String _text = text.getValue();
                if (_text.isEmpty() || !_text.contains("${")) {
                    return;
                }

                List<String> fragments = new ArrayList<>(
                    Arrays.asList(dollarVarPattern.split(_text)));
                if (fragments.size() == 1 && fragments.get(0).equals(_text)) {
                    return;
                }
                if (fragments.size() > 1) {
                    fragments.remove(fragments.size() - 1);
                }
                if (Boolean.FALSE.equals(SQLi.hasVulOnAdditiveFragments(fragments))) {
                    return;
                }

                Matcher m = dollarVarPattern.matcher(_text);
                while (m.find()) {
                    String suffix = _text.substring(m.end()).trim();
                    if (suffix.startsWith("=") || suffix.startsWith(">")
                        || suffix.startsWith("<")) {
                        // 暂时避免 (where|and|or) ${column} = #{var} 的问题
                        continue;
                    }

                    String var = m.group(1);
                    if (Boolean.FALSE.equals(ignoreVarName.contains(var))) {
                        holder.registerProblem(text, MESSAGE,
                            ProblemHighlightType.GENERIC_ERROR_OR_WARNING, mybatisXmlSQLiQuickFix);
                        break;
                    }
                }
            }
        };
    }

    public static class MybatisXmlSQLiQuickFix implements LocalQuickFix {

        @Override
        public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getFamilyName() {
            return QUICK_FIX_NAME;
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            // elem must be XmlText type
            fixXmlText((XmlText) descriptor.getPsiElement(), 0);
        }

        private void fixXmlText(XmlText xmlText, int offset) {
            String v = xmlText.getValue();
            Matcher m = dollarVarPattern.matcher(v);
            while (m.find(offset)) {
                String prefix = v.substring(0, m.start());
                String suffix = v.substring(m.end());

                if (suffix.trim().startsWith("=") || suffix.trim().startsWith(">")
                    || suffix.trim().startsWith("<")) {
                    // 暂时避免 where ${column} = #{var} 的问题
                    offset = m.end();
                    continue;
                }
                if (MybatisXmlSQLi.ignoreVarName.contains(m.group(1))) {
                    offset = m.end();
                    continue;
                }
                if (!SQLi.hasVulOnAdditiveFragments(Collections.singletonList(prefix))) {
                    offset = m.end();
                    continue;
                }

                if (whereInEndPattern.matcher(prefix).find()) {
                    // where in 型
                    if (Str.rtrim(prefix).endsWith("(") && Str.ltrim(suffix).startsWith(")")) {
                        prefix = Str.rtrim(prefix).substring(0, prefix.length() - 1);
                        suffix = Str.ltrim(suffix).substring(1);
                    }
                    XmlTag parent = xmlText.getParentTag();
                    if (parent != null) {
                        // 0. 保证文本结构，提取上文尾部换行符
                        PsiElement lastElem = xmlText.getLastChild();
                        PsiWhiteSpace lastWhiteSpace;
                        if (lastElem instanceof PsiWhiteSpace) {
                            lastWhiteSpace = (PsiWhiteSpace) lastElem;
                        } else {
                            lastWhiteSpace = (PsiWhiteSpace) PsiParserFacade.SERVICE
                                .getInstance(xmlText.getProject()).createWhiteSpaceFromText("\n");
                        }
                        // 1.先用前缀替换存在问题的文本
                        xmlText.setValue(prefix + lastWhiteSpace.getText());

                        // 2.向后添加foreach标签块
                        XmlTag foreach = createForeachXmlTag(m.group(1), parent, lastWhiteSpace);
                        parent.addAfter(foreach, xmlText);

                        // 3. 补齐尾部文本
                        XmlTag tagFromText = XmlElementFactory.getInstance(xmlText.getProject())
                            .createTagFromText("<a>" + lastWhiteSpace.getText() + suffix + "</a>");
                        XmlText[] textElements = tagFromText.getValue().getTextElements();
                        XmlText suffixXmlText;
                        if (textElements.length == 0) {
                            suffixXmlText = (XmlText) ASTFactory.composite(XmlElementType.XML_TEXT);
                        } else {
                            suffixXmlText = textElements[0];
                        }

                        // 4. 先追加再修正尾部文本
                        parent.add(suffixXmlText);

                        XmlTagChild[] xmlTagChildren = parent.getValue().getChildren();
                        if (xmlTagChildren[xmlTagChildren.length - 1] instanceof XmlText) {
                            fixXmlText((XmlText) xmlTagChildren[xmlTagChildren.length - 1], 0);
                        }
                    } else {
                        fixXmlText(xmlText, m.end());
                    }
                } else if (likeEndPatterh.matcher(prefix).find()) {
                    // like 型
                    String concat = " CONCAT('%', " + m.group().replace('$', '#') + ", '%') ";
                    prefix = StringUtils.stripEnd(prefix, "'\"% \n\r");
                    suffix = StringUtils.stripStart(suffix, "'\"% ");

                    xmlText.removeText(prefix.length(), v.length());
                    xmlText.insertText(concat + suffix, prefix.length());
                    fixXmlText(xmlText, prefix.length() + concat.length());
                } else {
                    if (prefix.trim().endsWith("'") || prefix.trim().endsWith("\"")) {
                        prefix = Str.rtrim(prefix).substring(0, prefix.length() - 1);
                        suffix = Str.ltrim(suffix).substring(1);
                    }
                    xmlText.setValue(prefix + "#{" + m.group(1) + "}" + suffix);
                    fixXmlText(xmlText, prefix.length() + m.group().length());
                }
                break;
            }
        }

        private XmlTag createForeachXmlTag(String varName, XmlTag parent,
                                           PsiWhiteSpace whiteSpace) {
            XmlTag foreach = parent.createChildTag("foreach", parent.getNamespace(),
                String.format("%s#{%sItem}%s", whiteSpace.getText(), varName, whiteSpace.getText()),
                false);
            foreach.setAttribute("collection", varName);
            foreach.setAttribute("item", varName + "Item");
            foreach.setAttribute("open", "(");
            foreach.setAttribute("separator", ",");
            foreach.setAttribute("close", ")");
            return foreach;
        }

    }
}
