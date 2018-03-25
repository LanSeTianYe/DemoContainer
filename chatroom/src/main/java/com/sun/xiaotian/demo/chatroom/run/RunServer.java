package com.sun.xiaotian.demo.chatroom.run;

import com.sun.xiaotian.demo.chatroom.server.ChatServer;

public class RunServer {
    public static void main(String[] args) {
        new ChatServer(9999).start();
    }
}
