package com.kvn.blade.core.sender;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kvn.blade.core.RemoteInfo;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
* @author wzy
* @date 2017年11月17日 上午11:25:25
*/
public class HttpSender implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);
	private static final OkHttpClient client = new OkHttpClient();
	private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

	@Override
	public String send(Object msg, RemoteInfo remoteInfo) {
		String type = remoteInfo.getAdditionProps().getProperty("type");
		String url = getUrl(remoteInfo);
		
		logger.info("post http request, url:{}, msg:{}", url, JSON.toJSONString(msg));
		if(type == null || "GET".equals(type.toUpperCase())){
			return doGet(url, msg);
		} else {
			return doPost(url, msg);
		}
	}

	private String doPost(String url, Object msg) {
		String jsonMsg = JSON.toJSONString(msg);
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
