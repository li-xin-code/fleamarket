package com.lixin.servlet;

import com.lixin.model.vo.UserVo;
import com.lixin.service.TokenService;
import com.lixin.service.impl.InMemoryTokenServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lixin
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = -60630888820314350L;

    private final TokenService<UserVo> tokenService = InMemoryTokenServiceImpl.getTokenService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String token = req.getHeader("token");
        if (token == null) {
            return;
        }
        tokenService.removeToken(token);
    }

}
