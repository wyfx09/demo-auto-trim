package com.example.hlw.utils;

/**
 * 通用的处理工具
 */
public class CommonUtils  {

    /**
     * 判断类是否是基础的数据类型
     * @param clz
     * @return
     */
    private static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }
}
