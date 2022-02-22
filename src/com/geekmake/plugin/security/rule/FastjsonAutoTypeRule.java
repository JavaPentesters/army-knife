package com.geekmake.plugin.security.rule;

import org.jetbrains.annotations.NotNull;

import com.geekmake.plugin.security.fix.DeleteElementQuickFix;
import com.geekmake.plugin.security.utils.ExpressionUtils;
import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;

/**
 * 17winSec 1002: Fastjson 打开autotype存在反序列化功能
 *  1、JVM启动参数
 *  -Dfastjson.parser.autoTypeSupport=true
 *
 *  2、代码中设置
 *  ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
 *
 *  
 *  https://github.com/alibaba/fastjson/wiki/enable_autotype
 *
 *
 * @author pez1420@gmail.com
 * @version $Id: FastjsonAutoTypeCheck.java v 0.1 2020/12/24 1:35 下午 pez1420 Exp $$
 */
public class FastjsonAutoTypeRule extends AbstractBaseJavaLocalInspectionTool {

    public static final String  MESSAGE        = "17winSec: Fastjson 反序列化风险";
    private static final String QUICK_FIX_NAME = "!Fix: 移除 setAutoTypeSupport";

    /**
     * This method is overridden to provide a custom visitor.
     * that inspects expressions with relational operators '==' and '!='.
     * The visitor must not be recursive and must be thread-safe.
     *
     * 覆盖AbstractBaseJavaLocalInspectionTool#buildVisitor接口，自定义visitor对象。visitor对象便利的目的是检查表达式是否使用了'==' and '!='
     * visito必须是线程安全的且是递并不能递归的
     * ProblemsHolder收集并持有visitor发现的问题
     * isOnTheFly：ture表示检查以非批量模式运行
     *
     * @param holder     object for visitor to register problems found.
     * @param isOnTheFly true if inspection was run in non-batch mode
     * @return non-null visitor for this inspection.
     * @see JavaElementVisitor
     */
    @Override
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitMethodCallExpression(PsiMethodCallExpression expression) {
                if (ExpressionUtils.hasFullQualifiedName(expression,
                    "com.alibaba.fastjson.parser.ParserConfig", "setAutoTypeSupport")) {
                    PsiExpression[] args = expression.getArgumentList().getExpressions();
                    if (args.length == 1 && args[0] instanceof PsiLiteralExpression
                        && Boolean.TRUE.equals(((PsiLiteralExpression) args[0]).getValue())) {
                        holder.registerProblem(expression, MESSAGE,
                            ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                            new DeleteElementQuickFix(expression, QUICK_FIX_NAME));
                    }
                }
            }
        };
    }
}
