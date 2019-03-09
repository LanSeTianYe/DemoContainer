package com.xiaotian.demo.test.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class TestInetAddress {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println(Arrays.asList(InetAddress.getAllByName("www.sunfeilong.com")));
        System.out.println(Arrays.asList(InetAddress.getAllByName("www.sunfeilong.cn")));
        System.out.println(Arrays.asList(InetAddress.getAllByName("www.baidu.com")));

        System.out.println(new InetSocketAddress("www.sunfeilong.com", 8080).getHostName());
        System.out.println(new InetSocketAddress("www.baidu.com", 8080).getHostName());
    }
}
