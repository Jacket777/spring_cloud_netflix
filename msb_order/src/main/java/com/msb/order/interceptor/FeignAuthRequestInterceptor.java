package com.msb.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignAuthRequestInterceptor implements RequestInterceptor {
    private String tokenId;

    public FeignAuthRequestInterceptor(String tokenId){
        this.tokenId = tokenId;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization",tokenId);
    }
}
