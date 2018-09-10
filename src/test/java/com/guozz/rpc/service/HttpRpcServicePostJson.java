package com.guozz.rpc.service;

import com.czb.coupon.sdk.request.CouponRequest;
import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.guozz.blade.anno.Addition;
import com.guozz.blade.anno.RequestParamStrategy;
import com.guozz.blade.anno.RequestTypeStrategy;
import com.guozz.blade.anno.RpcService;
import org.ipanda.common.utils.wrap.CommonPageWrapper;

import java.util.List;

/**
 * 郭智忠
 * 请求实例
 */
@RpcService(protocol = "http", host = "couponws",configHost = false)
public interface HttpRpcServicePostJson {
	
	@Addition(requestType = RequestTypeStrategy.POST,
			  requestParamType = RequestParamStrategy.JSON,
			  url ="services/coupon/queryListWithPage")
	CommonPageWrapper<List<CouponResponseDto>> queryListWithPage(CouponRequest request);

}
