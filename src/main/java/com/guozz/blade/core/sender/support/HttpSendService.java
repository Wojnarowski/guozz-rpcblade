package com.guozz.blade.core.sender.support;

public interface HttpSendService {

    public  String doSend(String url, Object paramObject, Class<?> returnType);
}
