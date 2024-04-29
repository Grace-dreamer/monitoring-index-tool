package com.monitoring.index.annotation;

import java.lang.annotation.*;

/**
 * @author Grace
 * @date 2024/4/28 - 11:22
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExportProperty
{
	String[] value() default {""};

	int index() default -1;

	int order() default Integer.MAX_VALUE;
}
