package com.sun.xiaotian.demo.mockito.test.argument;


import org.mockito.ArgumentMatcher;

import java.io.Serializable;

public class OddNumberMatcher implements ArgumentMatcher<Integer>, Serializable {

    @Override
    public boolean matches(Integer argument) {
        return argument % 2 == 1;
    }
}
