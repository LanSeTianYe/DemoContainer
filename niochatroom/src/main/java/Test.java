import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description   :   <br/>
 * Project Name  :   niochatroom<br/>
 * Author        :   FieLong Sun<br/>
 * Date          :   2018-01-20  11:15<br/>
 */
public class Test {

    private final static String host = "127.0.0.1";
    private final static int port = 8989;

    private final static Object output_lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Server().start();
        TimeUnit.SECONDS.sleep(1);
        new Client().start();
//        new Client().start();
//        new Client().start();
    }

    private static class Client extends Thread {
        @Override
        public void run() {
            SocketChannel client = null;
            try {
                WritableByteChannel output = Channels.newChannel(System.out);

                client = SocketChannel.open(new InetSocketAddress(host, port));
                client.configureBlocking(false);

                byte[] threadId = (Thread.currentThread().getId() + "").getBytes();
                ByteBuffer header = ByteBuffer.allocate(threadId.length + 4);
                header.put(threadId);
                header.putChar(':');
                header.putChar(' ');

                ByteBuffer content = ByteBuffer.allocate(11);

                ByteBuffer end = ByteBuffer.allocate(2);
                end.putChar('\n');

                while (true) {
                    int readLength = client.read(content);
                    if (readLength > 0) {
                        header.flip();
                        content.flip();
                        end.flip();
                        synchronized (output_lock) {
                            output.write(header);
                            output.write(content);
                            output.write(end);
                        }
                    } else if (readLength == -1) {
                        break;
                    }
                    content.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Server extends Thread {

        @Override
        public void run() {
            try {
                //创建非阻塞的Server
                ServerSocketChannel server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress(host, port));
                server.configureBlocking(false);

                //server注册到Selector
                Selector selector = Selector.open();
                server.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {

                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keys.iterator();

                    while (keyIterator.hasNext()) {

                        SelectionKey nextKey = keyIterator.next();
                        keyIterator.remove();

                        if (nextKey.isAcceptable()) {
                            //服务器Server
                            ServerSocketChannel channel = (ServerSocketChannel) nextKey.channel();
                            //请求
                            SocketChannel request = channel.accept();
                            request.configureBlocking(false);

                            SelectionKey selectionKey = request.register(selector, SelectionKey.OP_WRITE);
                            //附带信息
                            selectionKey.attach("Hello NIO !");

                        } else if (nextKey.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) nextKey.channel();
                            String attachment = (String) nextKey.attachment();
                            byte[] bytes = attachment.getBytes();
                            //使用 warp 不会在内存上再分分配空间，而是直接使用数组里面的数据
                            //数组和缓冲区是相互关联的，改变一个的值，另外一个的值也会改变，相当于共享数据
                            ByteBuffer buffer = ByteBuffer.wrap(bytes);
                            CharBuffer charBuffer = buffer.asCharBuffer();
                            charBuffer.compact();
                            buffer.put(bytes);
                            buffer.flip();
                            socketChannel.write(buffer);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
