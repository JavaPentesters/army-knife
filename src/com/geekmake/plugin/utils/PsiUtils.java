package com.geekmake.plugin.utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.annotations.NonNull;
import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;

/**
 * @author pez1420@gmail.com
 * @version $Id: PsiUtils.java v 0.1 2020/3/30 2:51 下午 pez1420 Exp $$
 */
public class PsiUtils {

//    public static Collection<Object[]> fixtures() {
//        return Arrays.asList(new Object[][]{
//                {TypeName.BOOLEAN, PsiType.BOOLEAN},
//                {TypeName.BYTE, PsiType.BYTE},
//                {TypeName.CHAR, PsiType.CHAR},
//                {TypeName.DOUBLE, PsiType.DOUBLE},
//                {TypeName.FLOAT, PsiType.FLOAT},
//                {TypeName.INT, PsiType.INT},
//                {TypeName.LONG, PsiType.LONG},
//                {TypeName.SHORT, PsiType.SHORT},
//                {TypeName.VOID, PsiType.VOID},
//        });
//    }

    /**
     *  基于className类名查找类
     *
     * @param project Project
     *                当前项目
     * @param className  String
     *                当前类名名,如HelloWorld
     * @return PsiClass[]
     *              一个文件中可能会定义有多个Class
     */
    public static PsiClass[] getClassesByName(Project project, String className) {
        PsiShortNamesCache shortNamesCache = PsiShortNamesCache.getInstance(project);
        PsiClass[] classes = shortNamesCache.getClassesByName(className,
            GlobalSearchScope.allScope(project));
        return classes;
    }

    /**
     * 在Class中创建Method：
     *
     * @param project Project
     *                当前项目
     * @param aClass   PsiClass
     *                当前类型
     * @param methodSig String
     *                方法签名，如 public void test(){}
     */
    public static void addMethod(Project project, PsiClass aClass, String methodSig) {
        PsiElementFactory elementFactory = PsiElementFactory.SERVICE.getInstance(project);
        PsiMethod method = elementFactory.createMethodFromText(methodSig, null);
        aClass.add(method);
    }

    /**
     * Find VirtualFile in project by filename.
     */
    @NotNull
    public static VirtualFile firstMatchingVirtualFileInProject(Project project, String filename) {
        Collection<VirtualFile> files = FilenameIndex.getVirtualFilesByName(project, filename,
            GlobalSearchScope.allScope(project));
        assertTrue(String.format("Filename %s not found in project", filename), files.size() > 0);
        return files.iterator().next();
    }

    /**
     *
     * @param method PsiMethod
     *              Psi方法
     * @return PsiType[]
     *            返回类型列表
     */
    public static PsiType[] getParameterTypes(PsiMethod method) {
        final PsiParameter[] parameters = method.getParameterList().getParameters();
        final PsiType[] paramTypes = PsiType.createArray(parameters.length);
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypes[i] = parameters[i].getType();
        }
        return paramTypes;
    }

    public static List<String> getMethodTypes(PsiMethod psiMethod) {
        PsiType returnType = psiMethod.getReturnType();
        List<String> strings = new ArrayList<>();
        strings.add(returnType == null ? "" : returnType.getCanonicalText());
        for (PsiParameter parameter : psiMethod.getParameterList().getParameters()) {
            PsiType type = parameter.getType();
            boolean generic = type instanceof PsiClassType
                              && ((PsiClassType) type).resolve() instanceof PsiTypeParameter;
            strings.add((generic ? "<" : "") + type.getCanonicalText(false) + (generic ? ">" : ""));
            strings.add(parameter.getName());
        }

        return strings;
    }

}
