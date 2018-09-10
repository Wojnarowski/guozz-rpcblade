package com.kvn.blade.util;

public enum HttpSendServiceEnum {

    HttpPostForm(1,"httpPostFormSendService"),
    HttpPostJson(2,"httpPostJsonSendService"),
    HttpGetForm(3,"httpGetFormSendService"),
    HttpGetJson(4,"httpGetJsonSendService");

    private Integer code;

    private String httpSendServiceClass;

    private HttpSendServiceEnum(Integer code, String httpSendServiceClass) {
        this.code = code;
        this.httpSendServiceClass = httpSendServiceClass;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getHttpSendServiceClass() {
        return httpSendServiceClass;
    }

    public void setHttpSendServiceClass(String httpSendServiceClass) {
        this.httpSendServiceClass = httpSendServiceClass;
    }

    public static HttpSendServiceEnum getByCode(Integer code) {
        for (HttpSendServiceEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
