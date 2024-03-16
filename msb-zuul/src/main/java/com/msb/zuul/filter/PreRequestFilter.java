package com.msb.zuul.filter;

/**
 * 前置过滤器
 */
public class PreRequestFilter extends AbstractPreZuulFilter{
    @Override
    protected Object cRun() {
        context.getRequest().setAttribute("startTime",System.currentTimeMillis());
        return success();
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
