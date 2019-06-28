package com.xiaotian.datasource.dynamic.commoon;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class Result {

    private final boolean success;

    private final Object data;


    private Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static Result ofException(Exception e) {
        return new Result(false, e.getMessage());
    }

    public static Result ofData(Object data) {
        return new Result(true, data);
    }
}
