package com.kvn.rpc.service;

import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.FooRequest;
import com.kvn.rpc.dto.FooResponse;

/**
 * @author 郭智忠
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "http", host = "172.16.21.28",configHost = false)
public interface HttpRpcService {
	
//	@Addition("type=post&url=mockjsdata/35/refund/api/query/querySettlement")
//	FooResponse call1030(FooRequest request);

}
