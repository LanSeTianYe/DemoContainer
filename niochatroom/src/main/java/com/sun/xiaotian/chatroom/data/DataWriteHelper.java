package com.sun.xiaotian.chatroom.data;

import com.sun.xiaotian.chatroom.message.Message;
import com.sun.xiaotian.chatroom.util.JsonMessageParse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DataWriteHelper {

    private final ByteBuffer intBuffer = ByteBuffer.allocate(DataConstant.INT_BUFFER_SIZE);
    private final ByteBuffer longBuffer = ByteBuffer.allocate(DataConstant.LONG_BUFFER_SIZE);
    private ByteBuffer writeBuffer;

    public void writeInt(SocketChannel channel, int number) throws IOException {
        intBuffer.putInt(number);
        intBuffer.flip();
        channel.write(intBuffer);
        while (intBuffer.position() < intBuffer.limit()) {
            channel.write(intBuffer);
        }
        intBuffer.clear();
    }

    public void writeLong(SocketChannel channel, long number) throws IOException {
        longBuffer.putLong(number);
        longBuffer.flip();
        channel.write(longBuffer);
        while (longBuffer.position() < longBuffer.limit()) {
            channel.write(longBuffer);
        }
        longBuffer.clear();
    }

    public void writeMessage(SocketChannel channel, Message message) throws IOException {
        String messageStr = JsonMessageParse.writeToJson(message);
        int messageLength = messageStr.getBytes().length;
        writeBuffer = ByteBuffer.allocate(DataConstant.INT_BUFFER_SIZE + messageLength);
        writeBuffer.putInt(messageLength);
        writeBuffer.put(messageStr.getBytes());
        writeBuffer.flip();
        channel.write(writeBuffer);
        while (writeBuffer.position() < writeBuffer.limit()) {
            channel.write(writeBuffer);
        }
        writeBuffer.clear();
    }
}
