package com.xiaotian.demo.test.queue;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestDelayQueue {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<CustomDelay> delayQueue = new DelayQueue<>();
        for (int i = 0; i < 100000; i++) {
            delayQueue.add(new CustomDelay("name" + i, i, TimeUnit.SECONDS));
        }

        while (true) {
            CustomDelay customDelay = delayQueue.take();
            System.out.println(customDelay);
            if (null == delayQueue.peek()) {
                break;
            }
        }
    }

    static class CustomDelay implements Delayed {

        private final long startNanoTime = System.nanoTime();

        private final String name;

        /**
         * 延迟时间
         */
        private final long delayTime;

        /**
         * 延迟的时间单位
         */
        private final TimeUnit delayTimeUnit;


        CustomDelay(String name, long delayNanoTime, TimeUnit delayTimeUnit) {
            this.name = name;
            this.delayTime = delayNanoTime;
            this.delayTimeUnit = delayTimeUnit;
        }

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return unit.convert((startNanoTime + delayTimeUnit.toNanos(delayTime) - System.nanoTime()), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(@NotNull Delayed o) {
            return ((int) (this.getDelay(delayTimeUnit) - o.getDelay(delayTimeUnit)));
        }

        public String getName() {
            return name;
        }

        public long getDelayTime() {
            return delayTime;
        }

        @Override
        public String toString() {
            return "CustomDelay{" +
                    "startNanoTime=" + startNanoTime +
                    ", name='" + name + '\'' +
                    ", delayTime=" + delayTime +
                    '}';
        }
    }
}
