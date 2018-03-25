package com.sun.xiaotian.chatroom.storage;

import com.sun.xiaotian.chatroom.message.Message;

import java.util.List;


/**
 * 数据存储
 */
public interface DataStorage {

    /**
     * 向存储中添加一条消息
     * @param message  要添加的消息
     * @return 返回是否添加成功
     */
    public boolean add(Message message);

    /**
     * 获取指定范围的消息, start从0开始,不包含end在内的数据
     * @param start
     * @param end
     * @return
     */
    public List<Message> getMessages(int start, int end);

    /**
     * 根据Id获取消息，从0开始
     * @return
     */
    public Message getByIndex(int index);

    /**
     * 获取全部消息
     * @return
     */
    public List<Message> getAll();

    /**
     * 获取消息数量
     * @return
     */
    public int messageCount();

    /**
     * 是否已经有消息
     * @return
     */
    public boolean hasMessage();
}
