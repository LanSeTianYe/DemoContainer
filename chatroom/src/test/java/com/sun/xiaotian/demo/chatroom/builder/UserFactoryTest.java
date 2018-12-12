package com.sun.xiaotian.demo.chatroom.builder;

import com.sun.xiaotian.demo.chatroom.user.UserInfo;
import org.junit.Test;

public class UserFactoryTest {

    @Test
    public void getUser() {
        for (int i = 0; i < 10000; i++) {
            UserFactory userFactory = new UserFactory();
            UserInfo user = userFactory.getUser();
            System.out.println(user);
        }
    }
}