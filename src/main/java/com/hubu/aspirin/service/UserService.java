package com.hubu.aspirin.service;

public interface UserService {

    /**
     * 登录
     */
    boolean login(String username, String password);

    /**
     * 登出
     */
    boolean logout();
}
