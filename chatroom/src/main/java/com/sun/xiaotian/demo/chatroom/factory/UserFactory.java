package com.sun.xiaotian.demo.chatroom.factory;

import com.sun.xiaotian.demo.chatroom.user.UserInfo;
import com.sun.xiaotian.demo.chatroom.util.NameGenerateUtil;
import com.sun.xiaotian.demo.chatroom.util.uuid.TimeUUID;

/**
 * 用户信息构造器
 */
public class UserFactory {

    public UserInfo getUser() {
        return UserInfo.generateUser(TimeUUID.get(), NameGenerateUtil.generate());
    }
}
