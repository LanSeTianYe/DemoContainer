package com.sun.xiaotian.demo.chatroom.util.uuid;

import java.util.concurrent.TimeUnit;

public class TimeUUID {

    /**
     * 时间第几秒
     */
    private static long dateSecond = getSeconds();
    /**
     * 这一秒的第几次请求
     */
    private static long sequenceInSecond = 1L;

    public static synchronized String get() {
        long currSeconds = getSeconds();
        if (currSeconds != dateSecond) {
            sequenceInSecond = 1L;
            dateSecond = currSeconds;
        } else {
            sequenceInSecond++;
        }
        return String.format("%s-%s", dateSecond, sequenceInSecond);
    }

    private static long getSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }
}
