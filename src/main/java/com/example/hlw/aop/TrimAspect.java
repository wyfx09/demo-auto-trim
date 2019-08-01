package com.example.hlw.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by hlw on 2019/8/1.
 */
@Aspect
@Component
public class TrimAspect {

    @Pointcut("execution(public * com..*.*Controller.*(..))")
    public void doOperation() {

    }

    @Around("doOperation()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] obj = joinPoint.getArgs();
        int index = 0;
        for (Object argItem : obj) {
            if (argItem instanceof String) {
                obj[index] = ((String) argItem).trim();
            } else if (argItem instanceof Object) {
                obj[index] = this.trimObjectStringProperty(argItem);
            }
            index++;
        }
        return joinPoint.proceed(obj);
    }

    private Object trimObjectStringProperty(Object obj) throws Exception {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value instanceof String) {
                if (value != null || ((String) value).length() > 0) {
                    field.set(obj, value.toString().trim());
                }
            }
        }
        return obj;
    }
}
