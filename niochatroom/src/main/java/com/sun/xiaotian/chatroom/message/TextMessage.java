package com.sun.xiaotian.chatroom.message;

import java.util.Date;

public class TextMessage extends Message {

    public static final TextMessage NULL = new TextMessage();

    private String data;

    /**
     * 没有默认构造函数反序列化会失败
     */
    public TextMessage() {
        super();
    }

    public TextMessage(long clientId, Date sendTime, Date acceptTime, String data) {
        super(clientId, sendTime, acceptTime);
        this.data = data;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "data='" + data + '\'' +
                "} " + super.toString();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
