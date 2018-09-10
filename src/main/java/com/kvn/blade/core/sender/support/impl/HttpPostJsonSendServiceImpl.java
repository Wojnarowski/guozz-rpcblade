package com.kvn.blade.core.sender.support.impl;

import com.kvn.blade.core.sender.support.HttpSendService;
import okhttp3.*;
import org.ipanda.common.utils.serialize.JsonHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("httpPostJsonSendService")
public class HttpPostJsonSendServiceImpl  implements HttpSendService {

    private static final OkHttpClient client = new OkHttpClient.Builder()
                                                        .connectTimeout(10, TimeUnit.SECONDS)
                                                        .writeTimeout(10,TimeUnit.SECONDS)
                                                        .readTimeout(20, TimeUnit.SECONDS)
                                                        .build();
    @Override
    public String doSend(String url, Object paramObject, Class<?> returnType) {
        try {
            //将请求对象转化为json对象
            String paramJson= JsonHelper.toJson(paramObject);
            //MediaType  设置Content-Type 标头中包含的媒体类型值
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
            Request request = new Request.Builder()
                                        .url(url)//请求的url
                                        .post(requestBody)
                                        .build();
            //创建/Call
            Response response = client.newCall(request).execute();
            if(response.code()==200){
                return response.body().string();
            }else{
                throw new RuntimeException("发送http请求返回异常，url=" + url + ", msg=" + JsonHelper.toJson(paramObject));
            }
        }catch (Exception e){
            throw new RuntimeException("发送http请求异常，url=" + url + ", msg=" + JsonHelper.toJson(paramObject), e);
        }
    }
}
