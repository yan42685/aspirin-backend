package com.hubu.aspirin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文档示例
 *
 * @author alex
 */
@Api(tags = "测试API")
@RequestMapping("api/test")
@ApiSupport(author = "alexyan")
@RestController
@Validated
public class TestController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @ApiOperation("翻转字符串")
    @ApiImplicitParam(name = "str", value = "原始字符串", paramType = "query", dataType = "string")
    @GetMapping("reverse")
    public JsonWrapper<String> reverse(@NotBlank(message = "输入字符串不能为空") String str) {
        return new JsonWrapper<>(new StringBuilder(str).reverse().toString());
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "加数", dataType = "int"),
            @ApiImplicitParam(name = "y", value = "被加数", dataType = "int")
    })
    @GetMapping("add")
    public JsonWrapper<Integer> add(int x, int y) {
        return new JsonWrapper<>(x + y);
    }

    @ApiOperation("生成密码")
    @GetMapping("password")
    public JsonWrapper<String> reverse(String username, String rawPassword) {
        return new JsonWrapper<>(UserUtils.generatePassword(username, rawPassword));
    }

    @ApiOperation("api列表")
    @GetMapping("apiList")
    public JsonWrapper<List<String>> getRouterList() {
        Set<RequestMappingInfo> set = requestMappingHandlerMapping.getHandlerMethods().keySet();
        List<String> routerList = set.stream().map(RequestMappingInfo::toString).collect(Collectors.toList());
        return new JsonWrapper<>(routerList);
    }
}
