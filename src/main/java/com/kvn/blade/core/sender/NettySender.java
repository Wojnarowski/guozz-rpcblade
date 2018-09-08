package com.kvn.blade.core.sender;

import com.kvn.blade.core.RemoteInfo;

/**
* @author 郭智忠
* @date 2017年11月17日 上午11:25:25
*/
public class NettySender implements Sender {

	@Override
	public String send(Object msg, RemoteInfo remoteInfo) {
		System.out.println("使用Netty发送：" + msg);
		String[] host_port = remoteInfo.getHost().split(":");
		NettyClient client = new NettyClient(host_port[0], Integer.valueOf(host_port[1]));
		try {
			return client.send(msg.toString());
		} catch (Exception e) {
			throw new RuntimeException("rpc调用异常", e);
		}
	}


}
