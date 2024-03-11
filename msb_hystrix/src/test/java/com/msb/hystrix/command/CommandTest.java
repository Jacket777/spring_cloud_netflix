package com.msb.hystrix.command;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class CommandTest {

    @Test
    public void execute(){
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");
        /**1.执行方式： 同步执行
         * 2.它有两个方法，1个是execute,另一个是run
         * 如果直接调用run方法，那么前面的验证就不会执行了
         */
        String result = commandDemo.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("result = "+ result+" , speeding = "+(endTime-beginTime));
    }


    @Test
    public void queueTest()throws Exception{
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("queue");
        /**1.执行方式： 异步执行
         *
         */
        Future<String> queue = commandDemo.queue();
        long endTime = System.currentTimeMillis();
        System.out.println("future end, speeding: " +(endTime-beginTime));
        System.out.println("result =" +queue.get());
        long endTime2 = System.currentTimeMillis();
        System.out.println("queue end, speeding: " +(endTime2-beginTime));
    }


    @Test
    public void observerTest(){
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observer");
        Observable<String> observe = commandDemo.observe();
        /**
         * 1.执行方式: 阻塞式调用
         */
        String result = observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("result = "+ result+" , speeding = "+(endTime-beginTime));
    }

    @Test
    public void observerTest2() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observer");
        Observable<String> observe = commandDemo.observe();
        /**
         * 1.执行方式: 非阻塞式调用
         */
        CountDownLatch latch = new CountDownLatch(1);
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observer, onCompleted");
                latch.countDown();
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("observer, onError - throwable = "+e);
            }


            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("result = "+ result+" , speeding = "+(endTime-beginTime));
            }
        });
        latch.await();
    }



    @Test
    public void tobserverTest(){
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observer");
        Observable<String> observe = commandDemo.toObservable();
        /**
         * 1.执行方式: 阻塞式调用
         */
        String result = observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("result = "+ result+" , speeding = "+(endTime-beginTime));
    }


    @Test
    public void tobserverTest2() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observer");
        Observable<String> observe = commandDemo.toObservable();
        /**
         * 1.执行方式: 非阻塞式调用
         */
        CountDownLatch latch = new CountDownLatch(1);
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("to observer, onCompleted");
                latch.countDown();
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("to observer, onError - throwable = "+e);
            }


            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("result = "+ result+" , speeding = "+(endTime-beginTime));
            }
        });
        latch.await();
    }




}
