package com.xiaotian.demo.test.udp.group;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MuticastClient {

    private final static int bufferSize = 1024;

    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(20000);
        InetAddress inetAddress = InetAddress.getByName("224.0.0.5");
        //join group
        multicastSocket.joinGroup(inetAddress);

        byte[] buffer = new byte[bufferSize];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, bufferSize);
        int times = 0;
        while (true) {
            //receive message
            multicastSocket.receive(datagramPacket);
            System.out.println(String.format("receive: %s, message: %s", ++times, new String(buffer)));
        }
    }
}
