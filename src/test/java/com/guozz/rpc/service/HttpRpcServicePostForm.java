package com.guozz.rpc.service;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.guozz.blade.anno.Addition;
import com.guozz.blade.anno.RequestParamStrategy;
import com.guozz.blade.anno.RequestTypeStrategy;
import com.guozz.blade.anno.RpcService;
import com.guozz.rpc.dto.CouponRequestDto;
import org.ipanda.common.utils.wrap.Wrapper;

/**
 * 郭智忠
 * 请求实例
 */
@RpcService(protocol = "http", host = "couponws",configHost = false)
public interface HttpRpcServicePostForm {
	
	@Addition(requestType = RequestTypeStrategy.POST,
			  requestParamType = RequestParamStrategy.FORM,
			  url ="services/coupon/queryCouponByCouponId")
	Wrapper<CouponResponseDto> queryCouponByCouponId(CouponRequestDto couponRequestDto);

}
