package com.og.jrest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that the decorated method should only be called for
 * GET requests. If a string parameter is supplied, the routing will consider
 * the string to be the action name.
 * 
 * @author matthew.shoemaker
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Get {

	public String value() default "";

}
