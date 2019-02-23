package com.xiaotian.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class TestByteBuf {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.copiedBuffer("123", CharsetUtil.UTF_8);
        System.out.println(buffer.hasArray());
        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.readableBytes());
        System.out.println(new String(buffer.array(), CharsetUtil.UTF_8));
        System.out.println(buffer.release());

    }


    public void test(ChannelHandlerContext channelHandlerContext) {
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf heapBuffer = alloc.heapBuffer();
        ByteBuf directBuffer = alloc.directBuffer();
        CompositeByteBuf compositeBuffer = alloc.compositeBuffer();
        compositeBuffer.addComponent(heapBuffer);
        compositeBuffer.addComponent(directBuffer);
        System.out.println(compositeBuffer.hasArray());
    }

}
