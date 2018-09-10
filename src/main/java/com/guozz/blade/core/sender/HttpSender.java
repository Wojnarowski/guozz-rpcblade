package com.guozz.blade.core.sender;

import com.alibaba.fastjson.JSON;
import com.guozz.blade.util.HttpSendServiceEnum;
import com.guozz.blade.anno.RequestParamStrategy;
import com.guozz.blade.core.ExtensionLoader;
import com.guozz.blade.core.RemoteInfo;
import com.guozz.blade.core.sender.support.HttpSendService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 郭智忠
* @date 2017年11月17日 上午11:25:25
*/
public class HttpSender implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);
	private static final OkHttpClient client = new OkHttpClient();
	private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
	private static final ExtensionLoader<HttpSendService> httpServiceSender = ExtensionLoader.getExtensionLoader(HttpSendService.class);

	@Override
	public String send(Object obj, RemoteInfo remoteInfo) {
		String requestType = remoteInfo.getAdditionProps().getProperty("requestType");
		String requestParamType = remoteInfo.getAdditionProps().getProperty("requestParamType");
		String url = getUrl(remoteInfo);
		logger.info("请求方法 请求类型={}",requestType);
		logger.info("请求方法 请求参数类型={}",requestParamType);
		logger.info("请求方法 请求url={}",url);
		logger.info("请求方法 请求返回类型returnType={}",remoteInfo.getReturnType());
		logger.info("请求参数 obj:{}", JSON.toJSONString(obj));

		HttpSendService httpSendService =null;
		if(requestType == null || "GET".equals(requestType.toUpperCase())){
			httpSendService=(HttpSendService)httpServiceSender.getExtension(HttpSendServiceEnum.HttpGetForm.getHttpSendServiceClass());
		} else {
			if(requestParamType.equals(RequestParamStrategy.FORM.toString())){
				httpSendService=(HttpSendService)httpServiceSender.getExtension(HttpSendServiceEnum.HttpPostForm.getHttpSendServiceClass());
			}else{
				httpSendService=(HttpSendService)httpServiceSender.getExtension(HttpSendServiceEnum.HttpPostJson.getHttpSendServiceClass());
			}

		}
		return httpSendService.doSend(url,obj,remoteInfo.getReturnType());
	}


	private String getUrl(RemoteInfo remoteInfo) {
		String url = remoteInfo.getAdditionProps().getProperty("url");
		if(url == null){
			throw new RuntimeException("没有配置发送的url");
		}
		return "http://" + remoteInfo.getHost() + "/" + url;
	}


}
