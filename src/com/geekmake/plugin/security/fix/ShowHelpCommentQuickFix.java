package com.geekmake.plugin.security.fix;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import com.geekmake.plugin.security.utils.ExpressionUtils;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;

/**
 * @author pez1420@gmail.com
 * @version $Id: ShowHelpCommentQuickFix.java v 0.1 2020/10/14 1:46 上午 pez1420 Exp $$
 */
public class ShowHelpCommentQuickFix implements LocalQuickFix {

    private final String QUICK_FIX_NAME;
    private final String COMMENT;

    public ShowHelpCommentQuickFix(String name, String comment) {
        this.QUICK_FIX_NAME = name;
        this.COMMENT = comment;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getFamilyName() {
        return QUICK_FIX_NAME;
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElementFactory factory = JavaPsiFacade.getElementFactory(project);
        PsiComment comment = factory.createCommentFromText(COMMENT, null);

        PsiElement currElem = ExpressionUtils.getParentOfStatement(descriptor.getPsiElement());
        if (currElem == null) {
            currElem = ExpressionUtils.getParentOfField(descriptor.getPsiElement());
        }
        if (currElem == null) {
            return;
        }
        PsiElement prev = currElem.getPrevSibling();

        PsiElement prevParent;
        if (prev == null) {
            prevParent = currElem.getParent();
        } else {
            prevParent = prev.getParent();
        }
        prevParent.addAfter(comment, prev);
    }
}
