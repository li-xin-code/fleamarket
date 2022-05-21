package com.lixin.filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author lixin
 */
@WebFilter(filterName = "jsonFilter", urlPatterns = "/*")
public class JsonFilter extends GenericFilter {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private final static String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        HttpServletRequest req = (HttpServletRequest) request;
        String contentType = req.getContentType();
        if (JSON_CONTENT_TYPE.equals(contentType)) {
            BufferedReader reader = req.getReader();
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String content = builder.toString();
            Map<String, Object> params = JSON.parseObject(content);
            System.out.println(params);
            params.keySet().forEach(k -> req.setAttribute(k, params.get(k)));
        }
        chain.doFilter(req, response);
    }

}
