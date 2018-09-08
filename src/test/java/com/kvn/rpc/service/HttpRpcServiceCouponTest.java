package com.kvn.rpc.service;

import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.CouponRequestDto;
import org.ipanda.common.utils.wrap.Wrapper;

/**
 * @author wzy
 * @date 2017年11月13日 下午5:18:10
 */
@RpcService(protocol = "http", host = "couponws")
public interface HttpRpcServiceCouponTest {
	
	@Addition("type=post&url=services/coupon/queryCouponByCouponId")
	String queryCouponByCouponId(CouponRequestDto couponRequestDto);

}
