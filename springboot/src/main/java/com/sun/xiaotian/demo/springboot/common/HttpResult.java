package com.sun.xiaotian.demo.springboot.common;

import java.io.Serializable;

public class HttpResult implements Serializable {

    private boolean success;

    private Object data;

    public HttpResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
