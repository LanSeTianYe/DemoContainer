package com.xiaotian.demo.test.pattern;


import java.util.regex.Pattern;

public class TestOrigin {

    private static final Pattern pattern = Pattern.compile("((http://127\\.0\\.0\\.1)(.)*)|((http://localhost)(.)*)");

    public static void main(String[] args) {
        System.out.println(pattern.matcher("http://127.0.0.1:2222").matches());
        System.out.println(pattern.matcher("http://127.0.0.1:").matches());
        System.out.println(pattern.matcher("http://localhost:2222").matches());
        System.out.println(pattern.matcher("http://local2host:2222").matches());
        System.out.println(pattern.matcher("").matches());

        System.out.println("http://127.0.0.1:2222".contains("127.0.0.1"));
        System.out.println("http://127.0.0.1:".contains("127.0.0.1"));
        System.out.println("http://localhost:2222".contains("localhost"));
        System.out.println("http://local2host:2222".contains("localhost"));
    }
}
