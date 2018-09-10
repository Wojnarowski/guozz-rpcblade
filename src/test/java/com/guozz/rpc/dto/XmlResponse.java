package com.guozz.rpc.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.guozz.blade.anno.DecodeBy;

/**
* @author 郭智忠
* @date 2017年11月13日 下午5:23:20
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@DecodeBy(DecodeBy.XML)
public class XmlResponse {
	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "FooResponse [code=" + code + ", msg=" + msg + "]";
	}
	
}
