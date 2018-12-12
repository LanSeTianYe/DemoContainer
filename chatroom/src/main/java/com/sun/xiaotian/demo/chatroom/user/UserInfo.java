package com.sun.xiaotian.demo.chatroom.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户信息
 */

public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2787165523795817253L;

    /**
     * 用户ID
     */
    private final String id;

    /**
     * 用户名
     */
    private final String name;


    private UserInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 初始化用户信息
     */
    public static UserInfo generateUser(String userId, String name) {
        return new UserInfo(userId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(name, userInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


