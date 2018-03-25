package com.sun.xiaotian.chatroom.client;

import com.sun.xiaotian.chatroom.TypeInfo;
import com.sun.xiaotian.chatroom.data.ChannelDataWriter;
import com.sun.xiaotian.chatroom.data.ClientSendData;
import com.sun.xiaotian.chatroom.message.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

public class WriteClient extends Client {

    private final static Logger logger = LogManager.getLogger(WriteClient.class);

    /**
     * 输入quit结束客户端
     */
    private final String QUIT = "quit";

    private ChannelDataWriter channelDataWriter = new ChannelDataWriter();

    private final Scanner scanner = new Scanner(System.in);

    public WriteClient(String host, int port) {
        super("write", host, port);
    }

    @Override
    public void execute() {
        try {
            SocketChannel clientSocket = SocketChannel.open(new InetSocketAddress(host, port));

            while (true) {
                //读取下一行
                String message = scanner.nextLine();
                if (QUIT.equalsIgnoreCase(message)) {
                    break;
                } else {
                    writeMessage(clientSocket, message);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeMessage(SocketChannel channel, String context) throws IOException {
        //发送消息
        ClientSendData clientSendData = new ClientSendData();
        clientSendData.setClientType(TypeInfo.CLIENT_SEND);
        clientSendData.setClientId(id);
        clientSendData.setMessage(new TextMessage(id, new Date(), null, context));
        channelDataWriter.writeToClientSocket(channel, clientSendData);
        logger.info("writeData:\t" + clientSendData.getMessage().toString());
    }
}
