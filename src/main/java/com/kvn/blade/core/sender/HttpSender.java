package com.kvn.blade.core.sender;

import java.io.IOException;
import java.util.Map;

import com.kvn.blade.anno.RequestParamStrategy;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kvn.blade.core.RemoteInfo;

/**
* @author 郭智忠
* @date 2017年11月17日 上午11:25:25
*/
public class HttpSender implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);
	private static final OkHttpClient client = new OkHttpClient();
	private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

	@Override
	public String send(Object obj, RemoteInfo remoteInfo) {
		String requestType = remoteInfo.getAdditionProps().getProperty("requestType");
		String requestParamType = remoteInfo.getAdditionProps().getProperty("requestParamType");
		String url = getUrl(remoteInfo);
		logger.info("请求方法 请求类型={}",requestType);
		logger.info("请求方法 请求参数类型={}",requestParamType);
		logger.info("请求方法 请求url={}",url);
		logger.info("请求参数 obj:{}", JSON.toJSONString(obj));

		if(requestType == null || "GET".equals(requestType.toUpperCase())){
			return doGet(url, obj);
		} else {
			if(requestParamType.equals(RequestParamStrategy.FORM.toString())){
				return doPostForm(url, obj);
			}else{
				return doPostJson(url, obj);
			}

		}
	}

	/**
	 * post json请求
	 * @param url
	 * @param obj
	 * @return
	 */
	private String doPostJson(String url, Object obj) {
		return null;
	}

	/**
	 * post form表单请求
	 * @param url
	 * @param obj
	 * @return
	 */
	private String doPostForm(String url, Object obj) {
		String jsonMsg = JSON.toJSONString(obj);
		FormBody.Builder formbody = new FormBody.Builder();
		Map<String,String> map=JSON.parseObject(jsonMsg,Map.class);
		for (Object key : map.keySet()) {
			formbody.add(String.valueOf(key), String.valueOf(map.get(key)));
		}
		FormBody body = formbody.build();
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Call call = client.newCall(request);
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + jsonMsg, e);
		}

	}

	private String doPost(String url, Object obj) {
		String jsonMsg = JSON.toJSONString(obj);
		RequestBody body = RequestBody.create(JSON_TYPE, jsonMsg);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + jsonMsg, e);
		}
	}

	private String doGet(String url, Object msg) {
		// TODO Auto-generated method stub
		throw new RuntimeException("暂未实现");
	}

	private String getUrl(RemoteInfo remoteInfo) {
		String url = remoteInfo.getAdditionProps().getProperty("url");
		if(url == null){
			throw new RuntimeException("没有配置发送的url");
		}
		return "http://" + remoteInfo.getHost() + "/" + url;
	}


}
