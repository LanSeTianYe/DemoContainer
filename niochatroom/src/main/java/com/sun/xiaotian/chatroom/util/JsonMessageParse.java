package com.sun.xiaotian.chatroom.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xiaotian.chatroom.exception.ChatRoomException;
import com.sun.xiaotian.chatroom.message.Message;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 把数据转换为Json字符串或从Json字符串解析数据
 */
public class JsonMessageParse {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public synchronized static Message readFromJson(String jsonStr) {
        Message message;
        try {
            message = objectMapper.readValue(jsonStr, Message.class);
        } catch (IOException e) {
            throw new ChatRoomException(e.getMessage(), e);
        }
        return message;
    }

    public synchronized static String writeToJson(Message message) {
        String messageStr;
        try {
            messageStr = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new ChatRoomException(e.getMessage(), e);
        }
        return messageStr;
    }

    public synchronized static List<Message> readFromListJson(String jsonStr) {
        List<Message> messages;
        try {
            messages = objectMapper
                    .readerFor(new TypeReference<List<Message>>() {})
                    .readValue(jsonStr);
        } catch (IOException e) {
            throw new ChatRoomException("数据解析失败。", e);
        }
        return messages;
    }

    public synchronized static String writeToListJson(List<Message> messages) {
        String messageStr = "";
        try {
            messageStr = objectMapper
                    .writerFor(new TypeReference<List<Message>>() {})
                    .writeValueAsString(messages);
        } catch (IOException e) {
            throw new ChatRoomException("数据序列化失败。", e);
        }
        return messageStr;
    }
}
