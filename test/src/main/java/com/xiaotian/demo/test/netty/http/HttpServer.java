package com.xiaotian.demo.test.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http server
 */
public class HttpServer {

    private final static int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws InterruptedException {

        HttpServer httpServer = new HttpServer();
        httpServer.run(DEFAULT_PORT);

    }

    public void run(int port) throws InterruptedException {

        NioEventLoopGroup group1 = new NioEventLoopGroup();
        NioEventLoopGroup group2 = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .localAddress(port)
                    .group(group1, group2)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpAggregatorInitializer(false));

            ChannelFuture bindFuture = serverBootstrap.bind().sync();
            bindFuture.addListener((future) -> {
                if (future.isSuccess()) {
                    System.out.println("bind success ...");
                } else {
                    future.cause().printStackTrace();
                }
            });
            ChannelFuture closeFuture = bindFuture.channel().closeFuture().sync();
            closeFuture.addListener((future) -> {
                if (future.isSuccess()) {
                    System.out.println("close success ...");
                } else {
                    future.cause().printStackTrace();
                }
            });
        } catch (Exception e) {
            group1.shutdownGracefully();
            group2.shutdownGracefully();
        }
    }
}
