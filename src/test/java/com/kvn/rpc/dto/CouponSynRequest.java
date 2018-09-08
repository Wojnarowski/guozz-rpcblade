package com.kvn.rpc.dto;

/**
* @author wzy
* @date 2017年11月13日 下午5:20:53
*/
public class CouponSynRequest {
	private Integer pageNum;
	private Integer pageSize;
	private Integer count;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
