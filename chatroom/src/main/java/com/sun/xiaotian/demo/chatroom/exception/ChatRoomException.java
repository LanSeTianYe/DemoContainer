package com.sun.xiaotian.demo.chatroom.exception;

/**
 * 聊天室异常基类
 */
public class ChatRoomException extends RuntimeException {

    private static final long serialVersionUID = -7530773218790481028L;

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
