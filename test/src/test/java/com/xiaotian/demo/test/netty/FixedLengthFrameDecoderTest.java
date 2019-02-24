package com.xiaotian.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class FixedLengthFrameDecoderTest {

    @Test
    public void testFrameDecoder() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }
        ByteBuf input = buffer.duplicate();

        assertTrue(embeddedChannel.writeInbound(input.retain()));
        assertTrue(embeddedChannel.finish());

        ByteBuf read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        assertNull(embeddedChannel.readInbound());
        buffer.release();
    }

    @Test
    public void testFrameDecoder2() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }
        ByteBuf input = buffer.duplicate();

        assertFalse(embeddedChannel.writeInbound(input.readBytes(2)));
        assertTrue(embeddedChannel.writeInbound(input.readBytes(7)));

        assertTrue(embeddedChannel.finish());

        ByteBuf read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        read = embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);
        read.release();

        assertNull(embeddedChannel.readInbound());
        buffer.release();
    }
}