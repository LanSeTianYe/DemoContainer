package com.sun.xiaotian.chatroom.data;

import com.sun.xiaotian.chatroom.message.Message;

public class ClientSendData {

    public final static ClientSendData NULL = new ClientSendData();

    protected int clientType;

    protected long clientId;

    protected int messageSize;

    private Message message;

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
