package com.blankj.alibaba._002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by tyreke.xu on 2020-04-05.
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
