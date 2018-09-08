package com.kvn.rpc;

import javax.annotation.Resource;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.kvn.rpc.dto.*;
import com.kvn.rpc.service.HttpRpcServiceCouponTest;
import org.ipanda.common.utils.wrap.CommonPageWrapper;
import org.ipanda.common.utils.wrap.Wrapper;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.kvn.SpringBaseTest;
import com.kvn.rpc.service.HttpRpcService;
import com.kvn.rpc.service.NettyRpcService;
import com.kvn.rpc.service.SocketRpcService;

import java.util.List;

/**
* @author wzy
* @date 2017年11月13日 下午5:27:29
*/

public class MainTest extends SpringBaseTest {
	@Resource
	private SocketRpcService socketRpcService;
	@Resource
	private NettyRpcService nettyRpcService;
	@Resource
	private HttpRpcService httpRpcService;
	@Resource
	private HttpRpcServiceCouponTest httpRpcServiceCouponTest;

	@Test
	public void testFactoryBean(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = socketRpcService.call1030(request);
	}
	
	@Test
	public void testSocket(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = socketRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}
	
	@Test
	public void testHttp(){
		FooRequest request = new FooRequest();
		FooResponse response = httpRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}
	
	@Test
	public void testNetty(){
		XmlRequest request = new XmlRequest();
		request.setAge(12);
		request.setName("kvn");
		XmlResponse response = nettyRpcService.call1030(request);
		System.out.println("===>" + JSON.toJSONString(response));
	}

	@Test
	public void testCoupon(){
		CouponRequest couponRequest = new CouponRequest();
		couponRequest.setPageIndex(1);
		couponRequest.setPageSize(10);
		couponRequest.setStatus(1);
		CommonPageWrapper<List<CouponResponseDto>> response = httpRpcServiceCouponTest.queryCouponByStatusPageV2(couponRequest);
		System.out.println("===>" + JSON.toJSONString(response));
	}

	@Test
	public void testCouponSyn(){
		CouponSynRequest couponSynRequest = new CouponSynRequest();
		couponSynRequest.setCount(1000);
		couponSynRequest.setPageNum(1);
		couponSynRequest.setPageSize(500);
		Wrapper<?> response = httpRpcServiceCouponTest.synExpiredCoupon(couponSynRequest);
		System.out.println("===>" + JSON.toJSONString(response));
	}

}
