package com.xiaotian.demo.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP 客户端
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket(10000)) {
            byte[] message = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
            while (true) {
                datagramSocket.receive(datagramPacket);
                System.out.println(String.format("accept: %s", new String(datagramPacket.getData())));
            }
        }
    }
}
