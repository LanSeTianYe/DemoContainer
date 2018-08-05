package com.sun.xiaotian.demo.rxjava;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableDemo {

    public static void main(String[] args) {

        //从数组产生
        Observable.fromArray("test", "age", "world").forEach(System.out::println);

        //发送
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(100);
                emitter.onNext("" + i);
            }
            emitter.onComplete();
        }).subscribe(System.out::println);
    }
}
