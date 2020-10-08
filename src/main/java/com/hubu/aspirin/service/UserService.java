package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.model.dto.BulletinDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * 登录
     */
    boolean login(String username, String password, Boolean rememberMe);

    /**
     * 登出
     */
    boolean logout();


    /**
     * 修改密码
     */
    boolean modifyPassword(String oldPassword, String newPassword);

    /**
     * 分页查看公告
     */
    IPage<BulletinDTO> getBulletinPage(Integer current, Integer size);

    /**
     * 根据标题或内容分页查找公告
     */
    IPage<BulletinDTO> getBulletinPageByTitleOrContent(Integer current, Integer size, String queryString);

    /**
     * 修改头像
     */
    String updateAvatar(MultipartFile file);
}
