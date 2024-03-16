package com.msb.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
public class MyFilter extends ZuulFilter {

    /**
     * filter 类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre"; //前置 处理器
    }

    /**
     * filter 执行顺序，越小越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启过滤器
     * 为实际业务 决定是否开启 true为开启，false为关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 具体业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headName = headerNames.nextElement();
            log.error("headerName: {},  headerValue:{}",headName,request.getHeader(headName));
        }



        return null;
    }
}
