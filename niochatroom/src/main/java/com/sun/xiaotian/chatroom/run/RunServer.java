package com.sun.xiaotian.chatroom.run;

import com.sun.xiaotian.chatroom.server.ChatServer;

public class RunServer {
    public static void main(String[] args) {
        new ChatServer(9999).start();
    }
}
