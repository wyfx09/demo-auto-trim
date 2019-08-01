# demo-auto-trim
统一自动化trim字符串参数

实现方式很简单，就是写了一个切面，在切面中实现对请求参数（字符串类型且非空）进行trim操作
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
