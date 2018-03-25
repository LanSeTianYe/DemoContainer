package com.sun.xiaotian.chatroom.storage;

import com.sun.xiaotian.chatroom.exception.ChatRoomException;
import com.sun.xiaotian.chatroom.message.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 内存数据存储
 */
public class MemoryDataStorage implements DataStorage {

    List<Message> allMessages = Collections.synchronizedList(new LinkedList<Message>());


    public boolean add(Message message) {
        return allMessages.add(message);
    }


    public List<Message> getMessages(int start, int end) {
        if (start < 0 || end > allMessages.size()) {
            throw new ChatRoomException("获取消息失败，请确认范围是否正确！");
        }
        return allMessages.subList(start, end);
    }

    @Override
    public Message getByIndex(int index) {
        if (!hasMessage()) {
            return null;
        }
        if (index > allMessages.size() || index < 0) {
            return null;
        }

        return allMessages.get(index);
    }

    public List<Message> getAll() {
        return allMessages;
    }

    public int messageCount() {
        return allMessages.size();
    }

    @Override
    public boolean hasMessage() {
        return allMessages.size() > 0;
    }
}
