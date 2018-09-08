package com.kvn.rpc.service;

import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RequestParamStrategy;
import com.kvn.blade.anno.RequestTypeStrategy;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.CouponRequestDto;
import org.ipanda.common.utils.wrap.Wrapper;

/**
 * 郭智忠
 * 请求实例
 */
@RpcService(protocol = "http", host = "couponws")
public interface HttpRpcServiceCouponTest {
	
	@Addition(requestType = RequestTypeStrategy.POST,requestParamType = RequestParamStrategy.FORM,url ="services/coupon/queryCouponByCouponId")
	String queryCouponByCouponId(CouponRequestDto couponRequestDto);

}
