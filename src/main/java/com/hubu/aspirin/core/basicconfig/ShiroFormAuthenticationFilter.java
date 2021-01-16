package com.hubu.aspirin.core.basicconfig;

import cn.hutool.json.JSONUtil;
import com.hubu.aspirin.core.Result;
import com.hubu.aspirin.core.needconfig.ExceptionEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 重新onAccessDenied 实现shiro对未认证的页直接返回json 而不是重定向
 *
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
        Result<String> result = new Result<>(errorCode, errorMsg);
        String json = JSONUtil.parseObj(result, false).toString();
        out.write(json);
        out.flush();
        out.close();
        return false;
    }
}
