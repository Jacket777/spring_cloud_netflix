package com.msb.zuul.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class AccessLogFilter extends AbstractPostZuulFilter{
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        //从PreRequestFilter 中获取设置的请求时间
        Long startTime = (Long)context.get("startTime");
        String uri = request.getRequestURI();
        long duration = System.currentTimeMillis() - startTime;
        //从网关通过的请求都会打印这个日志
        log.info("uri:{}, duiration:{}",uri,duration);



        return success();
    }

    @Override
    public int filterOrder() {
        return 10;
    }
}
