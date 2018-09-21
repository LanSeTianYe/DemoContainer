package com.sun.xiaotian.demo.hystrix.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultT implements Serializable {

    private static final long serialVersionUID = -2437401530184790903L;

    private final Object data;
    private final boolean result;

    public static ResultT ofExcption(Exception e) {
        return new ResultT(e, false);
    }

    public static ResultT ofData(Object data) {
        return new ResultT(data, true);
    }

    public static ResultT ofData(Object data, boolean result) {
        return new ResultT(data, result);
    }
}
