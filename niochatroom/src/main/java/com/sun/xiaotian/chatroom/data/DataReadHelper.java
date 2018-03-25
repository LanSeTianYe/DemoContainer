package com.sun.xiaotian.chatroom.data;

import com.sun.xiaotian.chatroom.message.Message;
import com.sun.xiaotian.chatroom.util.JsonMessageParse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DataReadHelper {

    private final static Logger logger = LogManager.getLogger(DataReadHelper.class);
    private final static int INT_BUFFER_SIZE = 4;
    private final static int LONG_BUFFER_SIZE = 8;

    private final ByteBuffer intBuffer = ByteBuffer.allocate(INT_BUFFER_SIZE);
    private final ByteBuffer longBuffer = ByteBuffer.allocate(LONG_BUFFER_SIZE);

    public int readInt(SocketChannel channel) throws IOException {
        int result;
        while (intBuffer.position() < intBuffer.capacity()) {
            channel.read(intBuffer);
        }
        intBuffer.flip();
        result = intBuffer.getInt();
        intBuffer.clear();
        return result;
    }

    public long readLong(SocketChannel channel) throws IOException {
        long result;
        channel.read(longBuffer);
        while (longBuffer.position() < LONG_BUFFER_SIZE) {
            channel.read(longBuffer);
        }
        longBuffer.flip();
        result = longBuffer.getLong();
        longBuffer.clear();
        return result;
    }

    public Message readMessage(SocketChannel channel, int size) throws IOException {
        Message message;

        logger.debug("readBuffer:\t" + size);

        ByteBuffer readBuffer = ByteBuffer.allocate(size);

        channel.read(readBuffer);
        while (readBuffer.position() < size) {
            channel.read(readBuffer);
        }
        readBuffer.flip();
        message = JsonMessageParse.readFromJson(new String(readBuffer.array()));
        readBuffer.clear();
        return message;
    }
}
