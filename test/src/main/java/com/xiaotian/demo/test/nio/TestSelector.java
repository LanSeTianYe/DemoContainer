package com.xiaotian.demo.test.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class TestSelector {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.keys();
    }
}
