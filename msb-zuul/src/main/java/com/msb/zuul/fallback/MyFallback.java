package com.msb.zuul.fallback;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyFallback implements FallbackProvider {

    /**
     * 针对哪个路由降级，如果是星号，则表示全部路由降级
     * @return
     */

    @Override
    public String getRoute() {
        return "msb-order";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {

            }

            /**
             * 业务降级处理
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                Map result = new HashMap<>();
                result.put(200,"服务降级");
                String resultJSON = JSONObject.toJSONString(result);
                return new ByteArrayInputStream(resultJSON.getBytes(StandardCharsets.UTF_8));
            }

            /**
             * 设置Header
             * @return
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
