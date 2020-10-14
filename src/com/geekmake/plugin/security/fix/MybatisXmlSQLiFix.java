package com.geekmake.plugin.security.fix;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;

/**
 * 代码审查(code inspection)
 *
 * @author pez1420@gmail.com
 * @version $Id: MybatisXmlSQLiFix.java v 0.1 2020/10/14 11:06 上午 pez1420 Exp $$
 */
public class MybatisXmlSQLiFix implements LocalQuickFix {

    @Nls(capitalization = Nls.Capitalization.Sentence)
    @NotNull
    @Override
    public String getFamilyName() {
        return "!Fix: #{var}";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor problemDescriptor) {

    }
}
