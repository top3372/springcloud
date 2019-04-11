package com.haili.ins.common.security.access.prepost.annotations;

import java.lang.annotation.*;

/**
 * @Author: leon
 * @Date: 2019/4/11 10:29
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuth {
    String value();
}
