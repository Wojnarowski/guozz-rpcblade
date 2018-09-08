package com.kvn.rpc.dto;

/**
* @author 郭智忠
* @date 2017年11月13日 下午5:20:53
*/
public class CouponRequest {
	private Integer status;
	private Integer pageIndex;
	private Integer pageSize;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
