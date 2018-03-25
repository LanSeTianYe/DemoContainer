package com.sun.xiaotian.chatroom.client;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Client implements Runnable {

    private static AtomicLong num = new AtomicLong(0l);

    protected final long id = num.incrementAndGet();

    protected final String type;

    protected String host;
    protected int port;

    public Client(String type, String host, int port) {
        this.type = type;
        this.host = host;
        this.port = port;
    }

    public void run() {
       execute();
    }

    public abstract void execute();

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public long getId() {
        return id;
    }
}
