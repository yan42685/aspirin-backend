package com.hubu.aspirin.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.ExceptionEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 重新onAccessDenied 实现shiro对未认证的页直接返回json 而不是重定向
 * @author alex
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = httpResponse.getWriter();
        int errorCode = ExceptionEnum.NOT_LOGIN.getErrorCode();
        String errorMsg = ExceptionEnum.NOT_LOGIN.getErrorMsg();
        JsonWrapper<String> jsonWrapper = new JsonWrapper<>(errorCode, errorMsg);
        String json = JSONUtil.parseObj(jsonWrapper, false).toString();
        out.write(json);
        out.flush();
        out.close();
        return false;
    }
}
