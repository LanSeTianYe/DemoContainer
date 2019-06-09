package com.xiaotian.demo.test.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class TestHashedWheelTimer {

    public static void main(String[] args) {

        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100L, TimeUnit.MILLISECONDS);

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                hashedWheelTimer.newTimeout(new TimerTask() {
                    @Override
                    public void run(Timeout timeout) throws Exception {
                        System.out.println(timeout);
                    }
                }, i, TimeUnit.MILLISECONDS);
            }
        }).start();

        hashedWheelTimer.start();
    }
}
