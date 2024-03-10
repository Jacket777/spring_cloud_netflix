package com.msb.stock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        String authorization = request.getHeader("Authorization");
        log.info("获取的认证信息 Authorization: {}",authorization);
        if(authorization!=null && authorization.length()!=0){
            return true;
        }

        return false;
    }
}
