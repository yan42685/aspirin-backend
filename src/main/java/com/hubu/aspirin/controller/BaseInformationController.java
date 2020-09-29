package com.hubu.aspirin.controller;


import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex
 */
@Api(tags = "管理员")
@RequestMapping("api/administrator")
@RestController
public class BaseInformationController {
}
