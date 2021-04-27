package com.hubu.aspirin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.hubu.aspirin.core.Result;
import com.hubu.aspirin.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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
    public Result<String> reverse(@NotBlank(message = "输入字符串不能为空") String str) {
        return new Result<>(new StringBuilder(str).reverse().toString());
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "加数", dataType = "int"),
            @ApiImplicitParam(name = "y", value = "被加数", dataType = "int")
    })
    @GetMapping("add")
    public Result<Integer> add(int x, int y) {
        return new Result<>(x + y);
    }

    @ApiOperation("生成密码")
    @GetMapping("password")
    public Result<String> reverse(String username, String rawPassword) {
        return new Result<>(UserUtils.generatePassword(username, rawPassword));
    }

    @ApiOperation("api详情列表")
    @GetMapping("apiDetailList")
    public Result<List<String>> getRouterDetailList() {
        Set<RequestMappingInfo> set = requestMappingHandlerMapping.getHandlerMethods().keySet();

        List<String> routerDetailList = new ArrayList<>();
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> {
            String methodName = value.getMethod().getName();
//            String urls= key.getPatternsCondition().getPatterns().toString();
//            urls = urls.substring(1, urls.length()-1);
            String info = key.toString();
            info = info.substring(1, info.length() - 1); // 去掉中括号
            routerDetailList.add(methodName + ' ' + info);
        });
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
//            routerDetailList.add(requestMappingInfoHandlerMethodEntry.getValue().getMethod().getName()) ;
//        }
//        System.out.println(routerDetailList);
//
//        List<String> routerDetailList = set.stream().map(RequestMappingInfo::toString).collect(Collectors.toList());
        return new Result<>(routerDetailList);
    }

    @ApiOperation("api地址列表")
    @GetMapping("apiUrlList")
    public Result<List<String>> getRouterUrlList() {
        Set<RequestMappingInfo> set = requestMappingHandlerMapping.getHandlerMethods().keySet();

        List<String> routerUrlList = set.stream()
                .map(RequestMappingInfo::getPatternsCondition)
                .map(PatternsRequestCondition::getPatterns)
                .map(Set::toString)
                // 去掉中括号
                .map(str -> str.substring(1, str.length() - 1))
                .collect(Collectors.toList());
        return new Result<>(routerUrlList);
    }

    @ApiOperation("假装注册")
    @PostMapping("fakeRegister")
    public Result<Void> testRegister(String username, String password, String email, String nickname) {
        return new Result<>(null);
    }
}
