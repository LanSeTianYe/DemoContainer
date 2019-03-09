package com.xiaotian.demo.test.jvm;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface TestHeapOutOfMemory {

     static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Object());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
