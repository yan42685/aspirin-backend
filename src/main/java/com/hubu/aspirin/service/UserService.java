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

    /**
     * 只注册用户名和密码
     */
    boolean simpleRegister(String username, String password);
}
