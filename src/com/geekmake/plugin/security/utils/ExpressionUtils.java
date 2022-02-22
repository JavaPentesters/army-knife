package com.geekmake.plugin.security.utils;

import org.jetbrains.annotations.Nullable;

import com.intellij.psi.*;
import com.siyeh.ig.psiutils.MethodCallUtils;

/**
 * @author pez1420@gmail.com
 * @version $Id: ExpressionUtils.java v 0.1 2020/12/21 4:09 下午 pez1420 Exp $$
 */
public class ExpressionUtils {

    /**
     * 向上查找直到出现PsiField
     * @param element PsiElement
     * @return PsiField | null
     */
    @Nullable
    public static PsiField getParentOfField(PsiElement element) {
        while (!(element instanceof PsiField)) {
            if (element == null || element instanceof PsiClass) {
                return null;
            }
            element = element.getParent();
        }
        return (PsiField) element;
    }

    /**
     * 向上查找直到出现PsiStatement
     * @param element PsiElement
     * @return PsiStatement | null
     */
    @Nullable
    public static PsiStatement getParentOfStatement(PsiElement element) {
        while (!(element instanceof PsiStatement)) {
            if (element == null || element instanceof PsiMethod) {
                return null;
            }
            element = element.getParent();
        }
        return (PsiStatement) element;
    }

    /**
     * 向上查找到当前的ClassInitializer
     * @param element PsiElement
     * @return PsiClassInitializer | null
     */
    @Nullable
    public static PsiClassInitializer getParentOfClassInitializer(PsiElement element) {
        while (!(element instanceof PsiClassInitializer)) {
            if (element == null) {
                return null;
            }
            element = element.getParent();
        }
        return (PsiClassInitializer) element;
    }

    /**
     * 向上查找到当前的Method
     * @param element PsiElement
     * @return PsiMethod | null
     */
    @Nullable
    public static PsiMethod getParentOfMethod(PsiElement element) {
        while (!(element instanceof PsiMethod)) {
            if (element == null) {
                return null;
            }
            element = element.getParent();
        }
        return (PsiMethod) element;
    }

    public static boolean hasFullQualifiedName(PsiMethodCallExpression methodCall,
                                               String qualifiedName, String methodName) {
        String methodCallName = MethodCallUtils.getMethodName(methodCall);
        if (!methodName.equals(methodCallName)) {
            return false;
        }

        PsiMethod method = methodCall.resolveMethod();
        if (method == null) {
            return false;
        }

        PsiClass containingClass = method.getContainingClass();
        if (containingClass == null) {
            return false;
        }

        return qualifiedName.equals(containingClass.getQualifiedName());
    }

    public static boolean hasFullQualifiedName(PsiNewExpression newExpression,
                                               String qualifiedName) {
        if (newExpression.getClassReference() == null) {
            return false;
        }
        return qualifiedName.equals(newExpression.getClassReference().getQualifiedName());
    }

}
