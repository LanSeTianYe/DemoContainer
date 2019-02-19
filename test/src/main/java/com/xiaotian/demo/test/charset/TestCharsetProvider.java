package com.xiaotian.demo.test.charset;

import sun.nio.cs.StandardCharsets;

import java.nio.charset.Charset;

public class TestCharsetProvider {

    public static void main(String[] args) {
        StandardCharsets standardCharsets = new StandardCharsets();
        Charset charset = standardCharsets.charsetForName("UTF-8");
        System.out.println(charset);
    }
}
