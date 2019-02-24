package com.xiaotian.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;


public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new AbsIntegerEncoder());
        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 10; i++) {
            buffer.writeInt(i);
        }

        assertTrue(embeddedChannel.writeOutbound(buffer));
        assertTrue(embeddedChannel.finish());

        for (int i = 0; i < 10; i++) {
            assertEquals(i, ((int) embeddedChannel.readOutbound()));
        }

        assertNull(embeddedChannel.readOutbound());
    }
}