package com.msb.zuul.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 后置路由过滤器
 */
public abstract class AbstractPostZuulFilter extends AbstractZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
}
