package com.xiaotian.demo.test.udp.group;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.TimeUnit;

public class MuticastServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        MulticastSocket multicastSocket = new MulticastSocket();

        InetAddress inetAddress = InetAddress.getByName("224.0.0.5");
        byte[] message = new String("Hello !").getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(message, message.length, inetAddress, 20000);

        int times = 0;
        while (true) {
            multicastSocket.send(datagramPacket);
            System.out.println(String.format("times: [%s], message: %s", ++times, message));
            TimeUnit.SECONDS.sleep(3L);
        }
    }
}
