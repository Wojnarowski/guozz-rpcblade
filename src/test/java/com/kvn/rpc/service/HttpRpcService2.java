package com.kvn.rpc.service;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
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
@RpcService(protocol = "http", host = "couponws",configHost = false)
public interface HttpRpcService2 {
	
	@Addition(requestType = RequestTypeStrategy.POST,
			  requestParamType = RequestParamStrategy.FORM,
			  url ="services/coupon/queryCouponByCouponId")
	Wrapper<CouponResponseDto> queryCouponByCouponId(CouponRequestDto couponRequestDto);

}
