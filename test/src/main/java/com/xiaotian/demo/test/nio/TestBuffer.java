package com.xiaotian.demo.test.nio;

import java.nio.IntBuffer;

public class TestBuffer {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(1);
        intBuffer.mark();
        intBuffer.put(2);
        intBuffer.rewind();

        System.out.println(intBuffer.get());

        intBuffer.reset();
        System.out.println(intBuffer.get());
        System.out.println(intBuffer.get());
        intBuffer.reset();
        System.out.println(intBuffer.get());
        System.out.println(intBuffer.get());

        intBuffer.flip();
        System.out.println(intBuffer.get());
        System.out.println(intBuffer.get());
        intBuffer.clear();
    }
}