package com.example.hlw.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 通用的处理工具
 */
public class CommonUtils  {

    /**
     * 判断类是否是基础的数据类型
     * @param clz
     * @return
     */
    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * 判断一个熟悉是不是公共属性
     * @param field
     * @return
     */
    public static boolean isPublicProperty(Field field){
        return Modifier.isPublic(field.getModifiers());
    }
}
