package com.guozz.blade.core.sender.support.impl;

import com.alibaba.fastjson.JSON;
import com.guozz.blade.core.sender.support.HttpSendService;
import okhttp3.*;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("httpPostFormSendService")
public class HttpPostFormSendServiceImpl implements HttpSendService {

    private static final OkHttpClient client = new OkHttpClient.Builder()
                                                            .connectTimeout(10, TimeUnit.SECONDS)
                                                            .writeTimeout(10,TimeUnit.SECONDS)
                                                            .readTimeout(20, TimeUnit.SECONDS)
                                                            .build();

    @Override
    public String doSend(String url, Object paramObject, Class<?> returnType) {
        try {
            String jsonMsg = JSON.toJSONString(paramObject);
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

            Response response = client.newCall(request).execute();
            if(response.code()==200){
                return response.body().string();
            }else{
                throw new RuntimeException("发送http请求返回异常，url=" + url + ", msg=" + JsonHelper.toJson(paramObject));
            }

        } catch (IOException e) {
            throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + JsonHelper.toJson(paramObject), e);
        }
    }
}
