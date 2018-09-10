package com.kvn.rpc.service;

import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.XmlRequest;
import com.kvn.rpc.dto.XmlResponse;

/**
 * @author 郭智忠
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "socket", host = "localhost:8091",configHost = false)
public interface SocketRpcService {
	
	XmlResponse call1030(XmlRequest request);

}
