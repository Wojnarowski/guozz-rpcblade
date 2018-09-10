package com.guozz.rpc.service;

import com.guozz.blade.anno.RpcService;

/**
 * @author 郭智忠
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "http", host = "172.16.21.28",configHost = false)
public interface HttpRpcService {
	
//	@Addition("type=post&url=mockjsdata/35/refund/api/query/querySettlement")
//	FooResponse call1030(FooRequest request);

}
