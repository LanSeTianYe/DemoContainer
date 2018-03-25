package com.sun.xiaotian.chatroom.client;

import com.sun.xiaotian.chatroom.TypeInfo;
import com.sun.xiaotian.chatroom.data.ChannelDataReader;
import com.sun.xiaotian.chatroom.data.ChannelDataWriter;
import com.sun.xiaotian.chatroom.data.ClientSendData;
import com.sun.xiaotian.chatroom.data.ServerSendData;
import com.sun.xiaotian.chatroom.message.Message;
import com.sun.xiaotian.chatroom.message.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReadClient extends Client {

    private ChannelDataReader channelDataReader = new ChannelDataReader();
    private ChannelDataWriter channelDataWriter = new ChannelDataWriter();

    private final static Logger logger = LogManager.getLogger(ReadClient.class);

    public ReadClient(String host, int port) {
        super("read", host, port);
    }

    @Override
    public void execute() {
        Random random = new Random(37);
        while (true) {
            try (SocketChannel clientSocket = SocketChannel.open(new InetSocketAddress(host, port))) {
                clientSocket.configureBlocking(false);
                //发送信息，表明身份
                writeClientInfo(clientSocket);
                //读取信息
                while (true) {
                    readMessage(clientSocket);
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    private void writeClientInfo(SocketChannel channel) {
        ClientSendData clientSendData = new ClientSendData();
        clientSendData.setClientId(id);
        clientSendData.setClientType(TypeInfo.CLIENT_ACCEPT);
        clientSendData.setMessage(TextMessage.NULL);
        channelDataWriter.writeToClientSocket(channel, clientSendData);
    }

    private void readMessage(SocketChannel channel) throws IOException, InterruptedException {
        ServerSendData serverSendData = channelDataReader.readFromServerSocket(channel);
        List<Message> messages = serverSendData.getMessages();
        if (messages != null) {
            messages.forEach(message -> {
                System.out.println(message.toString());
            });
        }
    }
}
