package com.kourou.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})           //注解方法
@Retention(RetentionPolicy.RUNTIME)   //注解信息保留到运行时
public @interface Measurement {
    int iterations();                 //一共进行几组测试

    int countPerGroup();              //每组测试指定多少次方法
}
