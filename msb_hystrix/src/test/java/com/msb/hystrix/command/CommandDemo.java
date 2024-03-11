package com.msb.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 指定返回类型为String
 */
public class CommandDemo extends HystrixCommand<String> {
    private String name;

    protected CommandDemo(String name) {
        super(Setter.withGroupKey(
              HystrixCommandGroupKey.Factory.asKey("MSB-Command")
        ));
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 单次调用业务方法
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        Thread.sleep(800);
        String result = "Command hello world name: "+name;
        System.err.println(result+", currentThread - "+ Thread.currentThread().getName());
        return result;
    }
}
