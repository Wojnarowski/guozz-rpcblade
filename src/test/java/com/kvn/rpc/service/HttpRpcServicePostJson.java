package com.kvn.rpc.service;

import com.czb.coupon.sdk.request.CouponRequest;
import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RequestParamStrategy;
import com.kvn.blade.anno.RequestTypeStrategy;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.CouponRequestDto;
import org.ipanda.common.utils.wrap.CommonPageWrapper;
import org.ipanda.common.utils.wrap.Wrapper;

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
