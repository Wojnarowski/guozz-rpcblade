package com.kvn.blade.core.sender.support.impl;

import com.alibaba.fastjson.JSON;
import com.kvn.blade.core.sender.support.HttpSendService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("httpPostFormSendService")
public class HttpPostFormSendServiceImpl implements HttpSendService {

    private static final OkHttpClient client = new OkHttpClient();

    @Override
    public String doSend(String url, Object paramObject, Class<?> returnType) {
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
        try {
            Call call = client.newCall(request);
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + jsonMsg, e);
        }
    }
}
