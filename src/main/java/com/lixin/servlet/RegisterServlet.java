package com.lixin.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.common.exception.NotExpectedException;
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
 * @author lx
 */
@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -2360004289206502733L;

    private final UserService userService;
    private final TokenService<UserVo> tokenService = InMemoryTokenServiceImpl.getTokenService();

    public RegisterServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = (String) req.getAttribute("username");
        String password = (String) req.getAttribute("password");
        String repeatPassword = (String) req.getAttribute("repeatPassword");
        if (!password.equals(repeatPassword)) {
            throw new NotExpectedException("The two passwords do not match.");
        }
        resp.setContentType("application/json");
        UserVo user = userService.register(username, password);
        String token = tokenService.getToken(user);
        PrintWriter writer = resp.getWriter();
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("userId", user.getUserId());
        writer.print(result);
    }

}
