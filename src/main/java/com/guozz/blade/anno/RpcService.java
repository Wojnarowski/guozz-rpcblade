package com.guozz.blade.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郭智忠
 * @date 2017年11月13日 下午4:48:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RpcService {
	String protocol();
	String host();
	boolean configHost();
}
