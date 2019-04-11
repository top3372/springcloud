package com.haili.ins.common.cloud.version.annotation;

import java.lang.annotation.*;

/**
 * header 版本 处理
 *
 * @author L.cm
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApiVersion {

	/**
	 * header 路径中的版本
	 *
	 * @return 版本号
	 */
	String value() default "";

}
