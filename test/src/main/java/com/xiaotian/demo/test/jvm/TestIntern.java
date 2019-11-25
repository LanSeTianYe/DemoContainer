package com.xiaotian.demo.test.jvm;

public class TestIntern {

    public static void main(String[] args) {
        String s1 = "java";
        String intern = s1.intern();
        System.out.println(s1 == intern);

        String s2 = "哈哈哈";
        String intern2 = s2.intern();
        System.out.println(s2 == intern2);
    }
}
