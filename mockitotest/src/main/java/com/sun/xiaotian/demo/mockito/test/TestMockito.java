package com.sun.xiaotian.demo.mockito.test;

import com.sun.xiaotian.demo.mockito.test.argument.EvenNumberMatcher;
import com.sun.xiaotian.demo.mockito.test.argument.OddNumberMatcher;
import com.sun.xiaotian.demo.mockito.test.model.User;
import com.sun.xiaotian.demo.mockito.test.service.UserService;
import org.junit.Test;
import org.mockito.Answers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestMockito {


    @Test
    public void testMockUser() {
        UserService userService = mock(UserService.class);

        //调用的时候返回数据
        when(userService.findAll()).then((invocation) -> {
            List<User> users = new ArrayList<>();
            users.add(mock(User.class, Answers.RETURNS_SMART_NULLS));
            users.add(mock(User.class, Answers.RETURNS_SMART_NULLS));
            users.add(mock(User.class, Answers.RETURNS_SMART_NULLS));
            users.add(mock(User.class, Answers.RETURNS_SMART_NULLS));
            return users;
        });

        userService.findAll().forEach(System.out::println);

        //验证方法调用了一次
        verify(userService).findAll();
    }

    @Test
    public void testMockList() {
        List list = mock(List.class);
        //anyInt 参数的匹配规则
        when(list.get(anyInt())).thenReturn("111111111");
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testArgumentMatcher() {
        List numbers = mock(List.class);

        //自定义参数匹配器
        when(numbers.get(intThat(new OddNumberMatcher()))).thenReturn("奇数");
        when(numbers.get(intThat(new EvenNumberMatcher()))).thenReturn("偶数");

        for (int i = 0; i < 10; i++) {
            System.out.println(numbers.get(i));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testVoid() {
        Object object = mock(Object.class);
        doThrow(new RuntimeException()).when(object).notify();
        object.notify();
    }
}
