package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("登录所需信息")
public class LoginTokenDTO {
    private RoleEnum role;
    private String username;
    private String password;
    private Boolean rememberMe;
}
