package com.xiaotian.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrameChunkDecoderTest {

    @Test
    public void testFrameChunk() {
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            byteBuf.writeByte(i);
        }

        ByteBuf input = byteBuf.duplicate();

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FrameChunkDecoder(3));

        assertTrue(embeddedChannel.writeInbound(input.readBytes(2)));
        try {
            embeddedChannel.writeInbound(input.readBytes(4));
            Assert.fail();
        } catch (TooLongFrameException e) {

        }
        assertTrue(embeddedChannel.writeInbound(input.readBytes(3)));

        ByteBuf read = embeddedChannel.readInbound();
        assertEquals(byteBuf.readSlice(2), read);

        read = embeddedChannel.readInbound();
        assertEquals(byteBuf.skipBytes(4).readSlice(3), read);

        read.release();
        byteBuf.release();
    }
}
