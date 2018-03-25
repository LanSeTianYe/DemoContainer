package com.sun.xiaotian.demo.chatroom.data;

import com.sun.xiaotian.demo.chatroom.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ServerSendData {

    private List<Message> messages = new ArrayList<>();

    public void add(Message message) {
        messages.add(message);
    }

    public void add(List<Message> messages) {
        messages.addAll(messages);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
