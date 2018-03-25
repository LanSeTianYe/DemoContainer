package com.sun.xiaotian.chatroom.util;

import com.sun.xiaotian.chatroom.message.Message;
import com.sun.xiaotian.chatroom.message.TextMessage;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MessageParseTest {

    private final static Logger logger = LogManager.getLogger(MessageParseTest.class);

    List<Message> messages;

    @Before
    public void before() {
        messages = new ArrayList<Message>();
        messages.add(new TextMessage(1l, new Date(), null, "ew1"));
        messages.add(new TextMessage(2l, new Date(), null, "ew2"));
        messages.add(new TextMessage(3l, new Date(), null, "ew3"));
    }

    @Test
    public void 测试对象解析和反解析() throws Exception {
        Message message = messages.get(0);
        String json = JsonMessageParse.writeToJson(message);
        Message message1 = JsonMessageParse.readFromJson(json);
        assertTrue(message.equals(message1));
    }


    @Test
    public void 测试列表序列化和反序列化() throws Exception {
        String json = JsonMessageParse.writeToListJson(messages);
        List<Message> result = JsonMessageParse.readFromListJson(json);
        assertTrue(messages.get(0).equals(result.get(0)));
        assertTrue(messages.get(1).equals(result.get(1)));
        assertTrue(messages.get(2).equals(result.get(2)));
    }

}