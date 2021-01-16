package com.salary.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 操作
     */
    String module() default "";

    /**
     * 级别
     */
    String info() default "";
}