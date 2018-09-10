package com.guozz.rpc.service;

import com.guozz.blade.anno.RpcService;
import com.guozz.rpc.dto.XmlRequest;
import com.guozz.rpc.dto.XmlResponse;

/**
 * @author 郭智忠
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "netty", host = "localhost:8091",configHost = false)
public interface NettyRpcService {
	
	XmlResponse call1030(XmlRequest request);

}
