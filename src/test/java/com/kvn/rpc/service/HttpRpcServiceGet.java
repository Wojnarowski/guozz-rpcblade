package com.kvn.rpc.service;

import com.czb.coupon.sdk.response.dto.CouponResponseDto;
import com.czb.gas.sdk.dto.GasInfoDo;
import com.czb.gas.sdk.dto.OilNumDo;
import com.kvn.blade.anno.Addition;
import com.kvn.blade.anno.RequestParamStrategy;
import com.kvn.blade.anno.RequestTypeStrategy;
import com.kvn.blade.anno.RpcService;
import com.kvn.rpc.dto.CouponRequestDto;
import com.kvn.rpc.dto.GasInfoRequest;
import org.ipanda.common.utils.wrap.Wrapper;

import java.util.List;

/**
 * 郭智忠
 * 请求实例
 */
@RpcService(protocol = "http", host = "gasws",configHost = false)
public interface HttpRpcServiceGet {
	
	@Addition(requestType = RequestTypeStrategy.GET,
			  requestParamType = RequestParamStrategy.FORM,
			  url ="services/gasInfoApp/getOilNumList")
	Wrapper<List<OilNumDo>> getOilNumList();


    @Addition(requestType = RequestTypeStrategy.GET,
            requestParamType = RequestParamStrategy.FORM,
            url ="services/gasInfoApp/getGasInfoByGasId")
    GasInfoDo getGasInfoByGasId(GasInfoRequest request);

}
