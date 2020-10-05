package com.geekmake.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;

public class JavaHelperAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前在操作的工程上下文
        Project project = e.getData(PlatformDataKeys.PROJECT);
        // 获取当前操作的类文件
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        // 获取当前类文件的路径
        String classPath = psiFile.getVirtualFile().getPath();
        String title = "Hello World!";
        Messages.showMessageDialog(project, classPath, title, Messages.getInformationIcon());
    }
}
