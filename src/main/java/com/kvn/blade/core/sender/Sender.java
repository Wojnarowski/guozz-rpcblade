package com.kvn.blade.core.sender;

import com.kvn.blade.core.RemoteInfo;

/**
* @author wzy
* @date 2017年11月16日 下午6:09:26
*/
public interface Sender {
	String send(Object msg, RemoteInfo remoteInfo);
}
