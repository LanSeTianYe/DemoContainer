package com.xiaotian.demo.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


/**
 * UDP 服务器
 */
public class UDPServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        try (DatagramSocket datagramSocket = new DatagramSocket(11000)) {
            long number = 0;
            while (true) {
                String message = String.valueOf(number);
                System.out.println(String.format("send: %s", message));
                //发送到 192.168.0.1~192.168.0.255 的所有机器
                DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), new InetSocketAddress("192.168.0.255", 10000));
                datagramSocket.send(datagramPacket);
                number++;
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
