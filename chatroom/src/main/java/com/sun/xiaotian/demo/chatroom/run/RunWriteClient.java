package com.sun.xiaotian.demo.chatroom.run;

import com.sun.xiaotian.demo.chatroom.client.WriteClient;

public class RunWriteClient {
    public static void main(String[] args) {
        new Thread(new WriteClient("127.0.0.1", 9999)).start();
    }
}
