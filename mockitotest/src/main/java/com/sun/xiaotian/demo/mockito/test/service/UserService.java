package com.sun.xiaotian.demo.mockito.test.service;

import com.sun.xiaotian.demo.mockito.test.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    List<User> findByName();
}
