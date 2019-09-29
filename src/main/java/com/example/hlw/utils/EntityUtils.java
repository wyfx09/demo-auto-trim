package com.example.hlw.utils;

import com.example.hlw.annotation.ApiModelProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityUtils {


    /**
     * 获取对象的实例
     * @param tClass
     * @param <T>
     * @return
     */
    public static  <T> T newInstane(Class<T> tClass){
        T t = new Gson().fromJson(getObjectJsonString(tClass),tClass);

        return t;
    }

    /**
     * 获取对象实例的json字符串
     * @param clazz
     * @return
     */
    public static String getObjectJsonString(Class clazz)  {

        StringBuilder sb = new StringBuilder();
        try {
            sb.append("{");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                if (apiModelProperty != null) {
                    sb.append(getFieldString(field.getName()));
                    field.setAccessible(true);
                    switch (field.getType().getSimpleName()) {
                        case "String":
                        case "string":
                            sb.append(":" + getFieldString(field.getName()));
                            break;
                        case "Integer":
                        case "int":
                        case "BigDecimal":
                        case "Double":
                        case "double":
                        case "BigInteger":
                        case "byte":
                        case "Byte":
                            sb.append(":" + "0");
                            break;
                        case "Date":
                            SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            sb.append(":" + "\"" + simpleFormatter.format(new Date()) + "\"");
                            break;
                        case "Boolean":
                        case "bool":
                        case "boolean":
                            sb.append(":" + "true");
                            break;
                        case "List":
                            Type genericType = field.getGenericType();
                            if (genericType instanceof ParameterizedType) {
                                if (!field.isAccessible()) {
                                    field.setAccessible(true);
                                }
                                ParameterizedType pt = (ParameterizedType) genericType;
                                //得到泛型里的class类型对象
                                Class<?> accountPrincipalApproveClazz = (Class<?>) pt.getActualTypeArguments()[0];

                                sb.append(":[" + getObjectJsonString(accountPrincipalApproveClazz) + "]");
                            }
                            break;
                        default:
                            if (!isJavaClass(field.getType())) {
                                sb.append(":" + getObjectJsonString(field.getType()));
                            } else {
                                sb.append(":" + getFieldString(field.getName()));
                            }
                            break;
                    }
                    sb.append(",");
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("创建实例失败");
        }
        int length = sb.length();
        String result = sb.toString();
        result = result.substring(0, length - 1);
        result += "}";


        return result;
    }

    private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);


    /**
     *获取对象的 描述
     * @param clazz
     * @return
     */
    public static String getObjectJsonDsecString(Class clazz)  {
        StringBuilder sbDesc = new StringBuilder();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.getModifiers();

                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                String propertyEnable = "非必填";
                NotNull notNull = field.getAnnotation(NotNull.class);
                NotBlank notBlank = field.getAnnotation(NotBlank.class);
                if(null != notNull){
                    propertyEnable = "必填";
                }else if(notBlank != null) {
                    propertyEnable = "必填 ";
                }
                if (apiModelProperty != null) {
                    sbDesc.append( StringUtils.rightPad( field.getType().getSimpleName(),12," ") +" || "+StringUtils.rightPad(  field.getName() ,30," ") + " || "+propertyEnable + " || " + StringUtils.rightPad( apiModelProperty.name(),50," ") + "\n");
                    field.setAccessible(true);
                    switch (field.getType().getSimpleName()) {
                        case "List":
                            sbDesc.append("start:++++++++++列表："+field.getName()+"+++++++++++++++\n");
                            Type genericType = field.getGenericType();
                            if (genericType instanceof ParameterizedType) {
                                if (!field.isAccessible()) {
                                    field.setAccessible(true);
                                }
                                ParameterizedType pt = (ParameterizedType) genericType;
                                //得到泛型里的class类型对象
                                Class<?> listPropertyClass = (Class<?>) pt.getActualTypeArguments()[0];
                                sbDesc.append(getObjectJsonDsecString(listPropertyClass));
                                sbDesc.append("end:++++++++++List+++++++++++++++\n");
                            }
                            break;
                        default:
                            if (!isJavaClass(field.getType())) {
                                sbDesc.append("start: ++++++++++++对象:"+field.getName()+"+++++++++++++\n");
                                sbDesc.append(getObjectJsonDsecString(field.getType()));
                                sbDesc.append("end: ++++++++++++对象+++++++++++++\n");
                            }

                            break;
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("反射创建实例失败");
        }

        return sbDesc.toString();
    }

    /**
     * 返回指定类的实例信息并答应对象的描述和报文
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T returnInstance(Class<T> tClass){
        logger.info("++++++++++++++++++++++="+tClass.getName()+"+++++++++++++++++++++++++");
        T t = newInstane(tClass);
       logger.info(new GsonBuilder().setPrettyPrinting().create().toJson( t));
       logger.info(getObjectJsonDsecString(tClass));
       return t;
    }

    private static String getFieldString(String name) {
        return "\"" + name + "\"";
    }

    private static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }
}
