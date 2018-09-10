package com.kvn.blade.core.sender.support.impl;

import com.kvn.blade.core.sender.support.HttpSendService;
import org.springframework.stereotype.Service;

@Service("httpGetFormSendService")
public class HttpGetFormSendServiceImpl implements HttpSendService {
    @Override
    public String doSend(String url, Object paramObject, Class<?> returnType) {
        return null;
    }
}
