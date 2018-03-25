package com.sun.xiaotian.demo.distributedlock.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xiaotian.demo.distributedlock.exception.DistributedLockException;
import com.sun.xiaotian.demo.distributedlock.LockInfo;

import java.io.IOException;
import java.util.List;

/**
 * 把数据转换为Json字符串或从Json字符串解析数据
 */
public class JsonParseUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public synchronized static LockInfo readFromJson(String jsonStr) {
        LockInfo message;
        try {
            message = objectMapper.readValue(jsonStr, LockInfo.class);
        } catch (IOException e) {
            throw new DistributedLockException(e.getMessage(), e);
        }
        return message;
    }

    public synchronized static String writeToJson(LockInfo message) {
        String messageStr;
        try {
            messageStr = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new DistributedLockException(e.getMessage(), e);
        }
        return messageStr;
    }

    public synchronized static List<LockInfo> readFromListJson(String jsonStr) {
        List<LockInfo> messages;
        try {
            messages = objectMapper
                    .readerFor(new TypeReference<List<LockInfo>>() {})
                    .readValue(jsonStr);
        } catch (IOException e) {
            throw new DistributedLockException("数据解析失败。", e);
        }
        return messages;
    }

    public synchronized static String writeToListJson(List<LockInfo> messages) {
        String messageStr = "";
        try {
            messageStr = objectMapper
                    .writerFor(new TypeReference<List<LockInfo>>() {})
                    .writeValueAsString(messages);
        } catch (IOException e) {
            throw new DistributedLockException("数据序列化失败。", e);
        }
        return messageStr;
    }
}
