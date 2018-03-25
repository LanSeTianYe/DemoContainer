package com.sun.xiaotian.chatroom.data;

import com.sun.xiaotian.chatroom.exception.ChatRoomException;
import com.sun.xiaotian.chatroom.message.Message;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class ChannelDataWriter {

    private final DataWriteHelper dataWriteHelper = new DataWriteHelper();

    public void writeToClientSocket(SocketChannel channel, ClientSendData clientSendData) {
        try {
            dataWriteHelper.writeInt(channel, clientSendData.getClientType());
            dataWriteHelper.writeLong(channel, clientSendData.getClientId());
            dataWriteHelper.writeMessage(channel, clientSendData.getMessage());
            dataWriteHelper.writeInt(channel, -1);
        } catch (IOException e) {
            throw new ChatRoomException("写入数据出错", e);
        }
    }

    public void writeToServerSocket(SocketChannel channel, ServerSendData serverSendData) {
        try {
            if (serverSendData != null) {
                for (Message message : serverSendData.getMessages()) {
                    dataWriteHelper.writeMessage(channel, message);
                }
            }
            dataWriteHelper.writeInt(channel, -1);
        } catch (IOException e) {
            throw new ChatRoomException("写入数据出错", e);
        }
    }
}
