package com.guozz.blade.core.sender.support.impl;

import com.guozz.blade.core.sender.support.HttpSendService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("httpGetFormSendService")
public class HttpGetFormSendServiceImpl implements HttpSendService {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
                                                        .connectTimeout(10, TimeUnit.SECONDS)
                                                        .writeTimeout(10,TimeUnit.SECONDS)
                                                        .readTimeout(20, TimeUnit.SECONDS)
                                                        .build();

    @Override
    public String doSend(String url, Object paramObject, Class<?> returnType) {
        /**
         * get请求中如果参数paramObject为null则不处理，直接拿url发起请求；
         * 如果paramObject不为空,则将paramObject转换为参数带到url后面请求
         */
        try {
            if(null==paramObject){
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = httpClient.newCall(request).execute();
                // 返回的是string 类型
                return response.body().string();
            }else{
               Map<String,String> map = JsonHelper.toMap(JsonHelper.toJson(paramObject),String.class,String.class);
               if(null==map){
                   Request request = new Request.Builder()
                           .url(url)
                           .build();
                   Response response = httpClient.newCall(request).execute();
                   // 返回的是string 类型
                   return response.body().string();
               }else{
                   url=url+"?";
                   for(Map.Entry<String,String> entry:map.entrySet()){
                       url=url+entry.getKey()+"="+entry.getValue()+"&";
                   }
                   url=url.substring(0,url.length());
                   Request request = new Request.Builder()
                           .url(url)
                           .build();
                   Response response = httpClient.newCall(request).execute();
                   // 返回的是string 类型
                   return response.body().string();
               }
            }
        } catch (Exception e) {
            throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + JsonHelper.toJson(paramObject), e);
        }

    }
}
