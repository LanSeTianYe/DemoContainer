package com.xiaotian.demo.test.nio;

import java.io.IOException;
import java.nio.channels.Selector;

public class TestSelector {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        selector.select();
    }
}
