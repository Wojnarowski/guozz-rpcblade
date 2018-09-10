package com.guozz.blade.util;

import java.util.Properties;

import com.guozz.blade.anno.RequestParamStrategy;
import com.guozz.blade.anno.RequestTypeStrategy;

import com.guozz.blade.anno.Addition;

/**
 * @author 郭智忠
 * @date 2017年11月17日 下午4:26:44
 */
public final class AdditionPropParser {

	private AdditionPropParser() {
	}

	public static Properties parse(Addition addition){
		if(addition == null){
			return null;
		}
		Properties props = new Properties();
		//请求类型
		RequestTypeStrategy requestTypeStrategy = addition.requestType();
		if(null == requestTypeStrategy){
			throw new IllegalStateException("配置错误：requestType为空");
		}
		if(requestTypeStrategy.equals(RequestTypeStrategy.POST)){
			props.setProperty("requestType",RequestTypeStrategy.POST.toString());
		}else{
			props.setProperty("requestType",RequestTypeStrategy.GET.toString());
		}
		//请求参数类型
		RequestParamStrategy requestParamStrategy = addition.requestParamType();
		if(null == requestParamStrategy){
			throw new IllegalStateException("配置错误：requestParamType为空");
		}
		if(requestParamStrategy.equals(RequestParamStrategy.FORM)){
			props.setProperty("requestParamType",RequestParamStrategy.FORM.toString());
		}else{
			props.setProperty("requestParamType",RequestParamStrategy.JSON.toString());
		}
		//请求url
		String url = addition.url();
		if(null == url){
			throw new IllegalStateException("配置错误：url为空");
		}
		props.setProperty("url",url);
		return props;
	}
}
