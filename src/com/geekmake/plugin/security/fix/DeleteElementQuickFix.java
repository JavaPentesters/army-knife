package com.geekmake.plugin.security.fix;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class DeleteElementQuickFix extends DeleteElementFix {

    public DeleteElementQuickFix(@NotNull PsiElement element, @NotNull @Nls String text) {
        super(element, text);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @Nullable Editor editor,
                       @NotNull PsiElement startElement, @NotNull PsiElement endElement) {
        super.invoke(project, file, editor, startElement, endElement);
    }
}
