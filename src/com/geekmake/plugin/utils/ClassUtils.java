package com.geekmake.plugin.utils;

/**
 * @author pez1420@gmail.com
 * @version $Id: ClassUtils.java v 0.1 2020/4/14 8:11 下午 pez1420 Exp $$
 */
public class ClassUtils {

    public static String getSimpleClassName(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }
}
