package com.xiaotian.demo.test.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class TestChannels {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ReadableByteChannel readableByteChannel = Channels.newChannel(new FileInputStream(Paths.get("D:\\qq.txt").toFile()));
        readableByteChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));
    }
}
