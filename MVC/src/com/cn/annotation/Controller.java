package com.cn.annotation;
 
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
 
 
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//这是代表运行的时候启动
@Documented
    public @interface Controller {
    String value() default "";
}