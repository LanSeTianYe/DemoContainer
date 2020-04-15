package com.xiaotian.demo.lambda.example.bdd;

import java.util.Collection;
import java.util.Objects;

public class Adjust {

    private final Object object;

    public Adjust(Object object) {
        this.object = object;
    }

    public boolean isNull() {
        return Objects.isNull(object);
    }

    public boolean nonNull() {
        return Objects.nonNull(object);
    }

    public boolean isEmpty() {
        return object instanceof Collection && ((Collection) object).isEmpty();
    }
}
