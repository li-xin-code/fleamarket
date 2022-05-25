package com.lixin.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.model.vo.UserVo;
import com.lixin.service.TokenService;
import com.lixin.service.UserService;
import com.lixin.service.impl.InMemoryTokenServiceImpl;
import com.lixin.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixin
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -7367452589063173652L;

    private final UserService userService;
    private final TokenService<UserVo> tokenService;

    public LoginServlet() {
        userService = new UserServiceImpl();
        tokenService = InMemoryTokenServiceImpl.getTokenService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = (String) req.getAttribute("username");
        String password = (String) req.getAttribute("password");
        if (username == null || password == null) {
            return;
        }
        UserVo loginUser = userService.login(username, password);
        String token = tokenService.getToken(loginUser);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("userId", loginUser.getUserId());
        writer.print(result);
    }

}
