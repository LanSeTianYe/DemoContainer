package com.xiaotian.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    private final int frame;

    public FixedLengthFrameDecoder(int frame) {
        if (frame < 0) {
            throw new IllegalArgumentException("frame mast grater than zero");
        }
        this.frame = frame;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >= this.frame) {
            out.add(in.readBytes(this.frame));
        }
    }
}
