package com.lixin.filter;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.common.enums.HttpStatus;
import com.lixin.common.exception.InvalidTokenException;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixin
 */
@WebFilter(filterName = "exceptionFilter", urlPatterns = "/*")
public class ExceptionFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            ((HttpServletResponse) response).setStatus(HttpStatus.ERROR.getCode());
            JSONObject result = new JSONObject();
            if (e instanceof InvalidTokenException) {
                result.put("errorCode", 1500);
            }
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            result.put("message", e.getMessage());
            writer.print(result);
        }
    }
}
