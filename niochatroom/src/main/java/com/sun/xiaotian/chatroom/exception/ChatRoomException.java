package com.sun.xiaotian.chatroom.exception;

public class ChatRoomException extends RuntimeException {

    public ChatRoomException() {
    }

    public ChatRoomException(String message) {
        super(message);
    }

    public ChatRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatRoomException(Throwable cause) {
        super(cause);
    }

    public ChatRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
