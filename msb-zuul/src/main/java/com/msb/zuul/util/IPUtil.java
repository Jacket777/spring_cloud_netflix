package com.msb.zuul.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

    /**
     * 获取请求的ip
     * 用户的真实的ip 不能通过getRemoteAddr
     * 因为用户可能使用代理
     * @return
     */
    public static String getRequestIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip==null || ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip==null || ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP-Client-IP");
        }


        if(ip==null || ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP-X_FORWARDED_FOR");
        }

        if(ip==null || ip.length()==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;

    }
}
