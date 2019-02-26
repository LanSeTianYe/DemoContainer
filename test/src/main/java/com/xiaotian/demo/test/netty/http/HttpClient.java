package com.xiaotian.demo.test.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * http client
 */
public class HttpClient {

    private final static int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws InterruptedException {
        HttpClient httpClient = new HttpClient();
        httpClient.run(DEFAULT_PORT);
    }

    public void run(int port) throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(loopGroup)
                    .remoteAddress("127.0.0.1", port)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpAggregatorInitializer(true));

            ChannelFuture connectFuture = bootstrap.connect().sync();
            connectFuture.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
