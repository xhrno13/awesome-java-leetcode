package com.blankj.alibaba._002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by tyreke.xu on 2020-04-05.
 * 说明1：题目里 "在外部资源不变的情况下" 这个我理解如果要满足尽可能的快这个要求 最好的方式是用本地cache
 * 并用mq广播变更来做本地cache的更新 初始化用并发(CountDownLatch)去做rpc调用维护好cache
 * 但是鉴于本题考查的是并发和线程池的使用，故不做延伸了
 * <p>
 * 说明2：关于线程池的核心参数的设置，这里是"拍脑袋"，具体参数的设置需要参考调用量以及rpc的响应时长来确定参数设置
 * corePoolSize和maximumPoolSize我设置成一样是因为减少线程池对队列满再创建maximumPoolSize的额外开销
 */
public class Solution {

    private static final long AWAIT_TIMEOUT = 3000L;

    private ExecutorService poolExecutor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(300),
            r -> new Thread(r, "payment_list_thread"),
            (r, executor) -> {
                //todo 记监控 打日志
            }
    );

    public List<String> filterDisablePayment(List<String> allPaymentList) {
        if (allPaymentList == null || allPaymentList.size() == 0) {
            return new ArrayList<>();
        }
        final PaymentRemoteSerivce paymentRemoteSerivce = new PaymentRemoteSerivce();
        final List<String> enableList = new ArrayList<>();
        final CountDownLatch countDownLatch = new CountDownLatch(allPaymentList.size());
        for (final String payment : allPaymentList) {
            poolExecutor.submit(() -> {
                try {
                    ConsultResult result = paymentRemoteSerivce.isEnabled(payment);
                    if (result.isEnable) {
                        enableList.add(payment);
                    }
                } catch (Exception ex) {
                    //todo 记监控 打日志
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await(AWAIT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            //todo 记监控 打日志
        }

        return enableList;
    }


    private class ConsultResult {
        /**
         * 咨询结果是否可用
         */
        private boolean isEnable;
        /**
         * 错误码
         */
        private String errorCode;
    }

    private class PaymentRemoteSerivce {

        ConsultResult isEnabled(String paymentType) {
            //to be implement, rpc invoke
            return new ConsultResult();
        }
    }
}
