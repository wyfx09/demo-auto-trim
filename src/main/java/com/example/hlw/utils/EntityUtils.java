package com.example.hlw.utils;

import java.lang.reflect.Field;

public class EnityUtils {


    public static   String trimObjectStringProperty(Class clazz) throws Exception {
       StringBuilder sb =new StringBuilder();
       sb.append("{");
       Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.get
            sb.append(getFieldString(field.getName()));
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value instanceof String) {
                 sb.append(":"+getFieldString(field.getName()));
            }
            sb.append(",");
        }
        int length = sb.length();
        String result = sb.toString();
        result = result.substring(0,length-1);
        result+="}";
        return result;
    }

    private static String getFieldString (String name){
        return "\""+name+"\"";
    }
}
