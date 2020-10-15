package com.geekmake.plugin.action;

import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.PsiUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;

/**
 * uast-lint-common:
 * https://github.com/yanex/uast-lint-common/blob/master/tools/base/lint/libs/lint-api/src/main/java/com/android/tools/lint/client/api/JavaEvaluator.java
 *
 * http://liumoran.cn/articleDetail/201
 *
 * https://juejin.im/post/5d56252ae51d4561b416d45a
 *
 * https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/deploying_plugin.html
 *
 * https://www.codota.com/code/java/methods/com.intellij.psi.PsiParameterList/getParameters
 *
 *
 * @author pez1420@gmail.com
 * @version $Id: BaseAnAction.java v 0.1 2020/3/26 6:15 下午 pez1420 Exp $$
 */
public abstract class BaseAnAction extends AnAction {

    /** 日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(ClipboardUtils.class);

    /**
     * 处理 PsiMethod
     *
     * //  int getResult(List<InputDO> inputDOs, String bizCode, Integer bizType);
     *
     *
     * @param project Project
     *                当前项目
     * @param psiMethod PsiMethod
     *                当前方法
     */
    public void handlePsiMethod(Project project, PsiMethod psiMethod) {

        final PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        if (ArrayUtils.isEmpty(parameters)) {
            return;
        }

        if (parameters.length == 1) {

        }

        for (int i = 0; i < parameters.length; i++) {
            PsiParameter parameter = parameters[i];

            // 处理每一个方法参数
            PsiType psiType = parameter.getType();
            String parameterName = parameter.getName();

            if (psiType instanceof PsiArrayType) {
                PsiArrayType psiArrayType = (PsiArrayType) psiType;
                handlePsiArrayType(project, psiArrayType);
            } else if (psiType instanceof PsiPrimitiveType) {
                PsiPrimitiveType psiPrimitiveType = (PsiPrimitiveType) psiType;
            } else if (psiType instanceof PsiClassType) {
                PsiClassType psiClassType = (PsiClassType)psiType;
                handleClassType(project, psiClassType);
            } else if (psiType instanceof PsiEllipsisType) {
                //...三点省略运算符
                PsiEllipsisType psiEllipsisType = (PsiEllipsisType) psiType;
                psiEllipsisType.toArrayType();

            }
        }
    }

    public void handleClassType(Project project, PsiClassType psiClassType) {
        String canonicalText = psiClassType.getCanonicalText();
        String className = psiClassType.getClassName();


        logger.info("className : {}", className);
    }

    public void handlePsiArrayType(Project project, PsiArrayType psiArrayType) {
        // com.geekmake.plugin.test.InputDO[]
        String canonicalText = psiArrayType.getCanonicalText();
        PsiType componentType = psiArrayType.getComponentType();

        // InputDO
        String className = ((PsiClassReferenceType) psiArrayType.getComponentType()).getClassName();
        // 读取Class
        VirtualFile virtualFile = PsiUtils.firstMatchingVirtualFileInProject(project,
            className + Constant.DOT_JAVA);
        PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        if (file instanceof PsiJavaFile) {
            PsiJavaFile javaFile = (PsiJavaFile) file;
            PsiClass[] classes = javaFile.getClasses();
            PsiField[] allFields = classes[0].getAllFields();
            for (PsiField field : allFields) {
                // static 和 final修饰符 跳过
                if (!field.hasModifierProperty(PsiModifier.STATIC)
                    || !field.hasModifierProperty(PsiModifier.FINAL)) {
                    continue;
                }

                // PsiEnumConstant
                if (field instanceof PsiEnumConstant) {
                    PsiEnumConstant psiEnumConstant = (PsiEnumConstant) field;
                    logger.info("psiEnumConstant : {}", psiEnumConstant);
                }

                final String name = field.getName();

            }

        }

        logger.info("componentType : {}", componentType);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }


        // 当前项目的编辑器
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        // 导航
        Object nav = event.getData(CommonDataKeys.NAVIGATABLE);
        // 获取当前操作的类文件
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        PsiElement psiElement = event.getData(CommonDataKeys.PSI_ELEMENT);
        IdeActionEvent actionEvent = new IdeActionEvent();
        actionEvent.setProject(project);
        actionEvent.setEditor(editor);

        if (psiElement instanceof PsiMethod) {
            PsiMethod psiMethod = (PsiMethod) psiElement;
            actionEvent.setClassName(psiMethod.getContainingClass().getQualifiedName());
            actionEvent.setMethodName(psiMethod.getNameIdentifier().getText());
            handlePsiMethod(project, psiMethod);
        }

        if (psiElement instanceof PsiClass) {
            PsiClass psiClass = (PsiClass) psiElement;
            actionEvent.setClassName(psiClass.getQualifiedName());
            actionEvent.setMethodName("*");
        }

        if (psiElement instanceof PsiField) {
            PsiField psiField = (PsiField) psiElement;
            actionEvent.setClassName(psiField.getContainingClass().getQualifiedName());
            actionEvent.setMethodName("*");
        }

        execute(actionEvent);
    }

    protected abstract void execute(IdeActionEvent actionEvent);
}
