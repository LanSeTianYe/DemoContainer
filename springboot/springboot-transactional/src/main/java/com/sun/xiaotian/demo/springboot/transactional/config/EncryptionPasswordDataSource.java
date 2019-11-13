package com.sun.xiaotian.demo.springboot.transactional.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * 加密数据源
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/9/26 20:35
 */
@Slf4j
public class EncryptionPasswordDataSource extends HikariDataSource {

    private String realUserName;
    private String realPassword;

    @PostConstruct
    public void init() {
        realPassword = decode(super.getPassword());
        realUserName = decode(super.getUsername());
    }

    @Override
    public String getPassword() {
        return realPassword;
    }

    @Override
    public String getUsername() {
        return realUserName;
    }

    private String decode(String userNameOrPassword) {
        return userNameOrPassword;
    }
}
