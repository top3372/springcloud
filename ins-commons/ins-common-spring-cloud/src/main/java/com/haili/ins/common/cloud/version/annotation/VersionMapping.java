package com.haili.ins.common.cloud.version.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * 版本号处理
 *
 * <p>
 *     1. url 版本号：添加到 url 前
 *     2. Accept 版本：application/vnd.blade.VERSION+json
 * </p>
 *
 * @author L.cm
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@ApiVersion
@Validated
public @interface VersionMapping {
	/**
	 * Alias for {@link RequestMapping#name}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
    String name() default "";

	/**
	 * Alias for {@link RequestMapping#value}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] value() default {};

	/**
	 * Alias for {@link RequestMapping#path}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] path() default {};

	/**
	 * Alias for {@link RequestMapping#params}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] params() default {};

	/**
	 * Alias for {@link RequestMapping#headers}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] headers() default {};

	/**
	 * Alias for {@link RequestMapping#consumes}.
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] consumes() default {};

	/**
	 * Alias for {@link RequestMapping#produces}.
	 * default json utf-8
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] produces() default {};

	/**
	 * Alias for {@link ApiVersion#value}.
	 * @return {String}
	 */
	@AliasFor(annotation = ApiVersion.class, attribute = "value")
    String apiVersion() default "";

}
