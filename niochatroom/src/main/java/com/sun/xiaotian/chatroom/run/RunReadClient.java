package com.sun.xiaotian.chatroom.run;

import com.sun.xiaotian.chatroom.client.ReadClient;

public class RunReadClient {
    public static void main(String[] args) {
        new Thread(new ReadClient("127.0.0.1", 9999)).start();
    }
}
