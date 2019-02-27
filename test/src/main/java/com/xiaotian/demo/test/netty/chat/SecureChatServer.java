package com.xiaotian.demo.test.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;


public class SecureChatServer extends ChatServer{

    private final SslContext sslContext;

    public SecureChatServer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    public ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
        return new SecureChatServerInitializer(channelGroup, sslContext);
    }

    public static void main(String[] args) throws CertificateException, SSLException {
        SelfSignedCertificate cert = new SelfSignedCertificate();
        SslContext context = SslContextBuilder.forServer(cert.certificate(), cert.privateKey()).build();
        SecureChatServer secureChatServer = new SecureChatServer(context);
        ChannelFuture channelFuture = secureChatServer.start(new InetSocketAddress(8888));
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                secureChatServer.destroy();
            }
        });
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
