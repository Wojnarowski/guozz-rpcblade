package com.guozz.blade.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 郭智忠
* @date 2017年11月16日 下午5:49:10
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EncodeBy {
	String value();
	
	String JSON = "json";
	String XML = "xml";
}
