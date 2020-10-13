package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * 登录
     */
    UserDTO login(String username, String password, Boolean rememberMe);

    /**
     * 登出
     */
    boolean logout();


    /**
     * 修改密码
     */
    boolean modifyPassword(String oldPassword, String newPassword);

    /**
     * 根据标题或内容分页查找公告
     */
    IPage<BulletinDTO> pageBulletin(Integer current, Integer size, String queryString);

    /**
     * 修改头像
     */
    String updateAvatar(MultipartFile file);
}
