package com.guozz.blade.core.sender;

import com.guozz.blade.core.RemoteInfo;

/**
* @author 郭智忠
* @date 2017年11月16日 下午6:09:26
*/
public interface Sender {
	String send(Object msg, RemoteInfo remoteInfo);
}
