package com.msb.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.msb.zuul.util.IPUtil;
import com.msb.zuul.util.RedisOperator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class BlackIPFilter extends ZuulFilter {
    @Value("${blackIp.contiueCounts}")
    private Integer continueCounts;


    @Value("${blackIp.timeIntervale}")
    private Integer timeIntervale;

    @Value("${blackIp.limitTimes}")
    private Integer limitTimes;

    @Autowired
    private RedisOperator redis;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.error("执行 ip 黑名单过滤器");
        log.info("限制条件: contiuneCounts: {} timeInterval:{} limitTimes:{}",continueCounts, timeIntervale,limitTimes);
        //获取上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取ip
        String ip = IPUtil.getRequestIP(request);
        /**
         * 判断ip 10秒内是否超过10次，如果超过，则限制ip访问15秒
         */
        final String ipRedisKey = "zuul-ip"+ip; //记录访问次数
        final String ipRedisLimitKey = "zuul-ip-limit"+ip;

        //获取这个ip对应key的剩余时间
        long limitLeftTime = redis.ttl(ipRedisLimitKey);
        //如果当前限制的ip的key还存在剩余时间，说明这个ip不能访问，需要继续等待
        if(limitLeftTime > 0){
            stopRequest(context);
            return null;
        }

        //在redis中累加请求访问的次数
        long requestCounts = redis.increment(ipRedisKey,1);
        //从0开始计数请求次数，初期是1 设置过期时间，也就是连续请求的时间间隔
        if(requestCounts == 1){
            redis.expire(ipRedisKey,timeIntervale);
        }

        //如果请求超过限制次数，就需要对ip进行限流
        if(requestCounts > continueCounts){
            redis.set(ipRedisLimitKey,ipRedisLimitKey,limitTimes);
            stopRequest(context);
        }

        return null;
    }

    private void stopRequest(RequestContext context) {
        //停止zuul继续向下路由，禁止请求通信
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(200);
        Map result = new HashMap<String,String>();
        result.put("message","对请求IP进行限流");
        String resultStr = JSONObject.toJSONString(result);
        context.setResponseBody(resultStr);
        context.getResponse().setCharacterEncoding("utf-8");
        context.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);

    }
}
