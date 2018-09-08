package com.kvn.rpc.service;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.CouponRequest;
import com.kvn.rpc.dto.CouponSynRequest;
import com.kvn.rpc.dto.FooRequest;
import com.kvn.rpc.dto.FooResponse;
import org.ipanda.common.utils.wrap.CommonPageWrapper;
import org.ipanda.common.utils.wrap.Wrapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wzy
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "http", host = "couponws")
public interface HttpRpcServiceCouponTest {
	
	@Addition("type=post&url=services/synCoupon/synExpiredCoupon")
	CommonPageWrapper<List<CouponResponseDto>> queryCouponByStatusPageV2(CouponRequest couponRequest);

	@Addition("type=post&url=services/synCoupon/synExpiredCoupon")
	Wrapper<?> synExpiredCoupon(CouponSynRequest couponSynRequest);

}
