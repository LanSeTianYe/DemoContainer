package com.sun.xiaotian.chatroom.run;

import com.sun.xiaotian.chatroom.client.WriteClient;

public class RunWriteClient {
    public static void main(String[] args) {
        new Thread(new WriteClient("127.0.0.1", 9999)).start();
    }
}
