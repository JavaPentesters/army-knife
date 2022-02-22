package com.geekmake.plugin.security.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.geekmake.plugin.security.BaseFixElementWalkingVisitor;
import com.intellij.psi.*;

/**
 *
 * @author pez1420@gmail.com
 * @version $Id: InspectionToolUtils.java v 0.1 2020/12/21 4:50 下午 pez1420 Exp $$
 */
public class InspectionToolUtils {

    /**
     * 本方法针对可利用安全设置修复的漏洞，例如：
     * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
     * dbf.setFeature(...);
     * 检查变量 (如dbf) 所在 scope 是否满足 visitor 的要求。
     * 若变量定义与使用分离(如dbf定义为类成员，但初始化在某一方法内)，则assignElem指初始化元素，resolvedElem为定义元素
     *
     * (1) 对于变量在方法/静态块/构造块内初始化， scope 为当前方法/静态块/构造块
     * (2) 对于变量是类成员变量，并且在定义时赋值，有两种情况
     * (2.1) 对于 static 成员变量，检查该类的静态块是否满足 visitor 要求
     * (2.2) 对于非 static 成员变量，检查该类的构造块是否满足 visitor 要求
     * @param assignElem  PsiElement
     * @param resolvedElem PsiElement
     * @param visitor PsiElementVisitor
     * @return boolean
     */
    public static boolean checkVariableUseFix(@Nullable PsiElement assignElem,
                                              @Nullable PsiElement resolvedElem,
                                              @NotNull BaseFixElementWalkingVisitor visitor) {
        PsiMethod method = ExpressionUtils.getParentOfMethod(assignElem);
        if (method != null) {
            method.accept(visitor);
            return visitor.isFix();
        }

        PsiClassInitializer initializer = ExpressionUtils.getParentOfClassInitializer(assignElem);
        if (initializer != null) {
            initializer.accept(visitor);
            return visitor.isFix();
        }

        if (resolvedElem instanceof PsiField) {
            PsiField field = (PsiField) resolvedElem;
            if (field.hasModifierProperty(PsiModifier.STATIC)) {
                return checkStaticInitializersHasFix((PsiClass) field.getParent(), visitor);
            } else {
                return checkConstructorHasFix((PsiClass) field.getParent(), visitor);
            }
        }

        return false;
    }

    public static boolean checkStaticInitializersHasFix(PsiClass aClass,
                                                        BaseFixElementWalkingVisitor visitor) {
        PsiClassInitializer[] initializers = aClass.getInitializers();
        for (PsiClassInitializer initializer : initializers) {
            if (initializer.hasModifierProperty(PsiModifier.STATIC)) {
                initializer.accept(visitor);
                if (visitor.isFix()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkConstructorHasFix(PsiClass aClass,
                                                 BaseFixElementWalkingVisitor visitor) {
        PsiClassInitializer[] initializers = aClass.getInitializers();
        for (PsiClassInitializer initializer : initializers) {
            if (!initializer.hasModifierProperty(PsiModifier.STATIC)) {
                initializer.accept(visitor);
                if (visitor.isFix()) {
                    return true;
                }
            }
        }

        PsiMethod[] constructors = aClass.getConstructors();
        for (PsiMethod constructor : constructors) {
            constructor.accept(visitor);
            if (visitor.isFix()) {
                return true;
            }
        }
        return false;
    }
}
