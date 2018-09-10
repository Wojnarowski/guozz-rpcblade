package com.kvn.blade.scan;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class RpcHostConfigurer implements InitializingBean {

    private String serviceHostDomain;

    public String getServiceHostDomain() {
        return serviceHostDomain;
    }

    public void setServiceHostDomain(String serviceHostDomain) {
        this.serviceHostDomain = serviceHostDomain;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(serviceHostDomain, "请配置服务域名(serviceHostDomain)的值，class=" + this.getClass().getName());
    }
}
