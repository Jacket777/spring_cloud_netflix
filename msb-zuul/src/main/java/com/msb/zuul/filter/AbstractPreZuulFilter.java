package com.msb.zuul.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 前置路由过滤器
 */
public abstract class AbstractPreZuulFilter extends AbstractZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
