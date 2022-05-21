package com.lixin.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.model.vo.UserVo;
import com.lixin.service.TokenService;
import com.lixin.service.UserService;
import com.lixin.service.impl.InMemoryTokenServiceImpl;
import com.lixin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author lixin
 */
@WebServlet(name = "userServlet", urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 7037437391071557329L;
    private final static String INFO = "info";
    private final static String USERNAME = "name";
    private final UserService userService = new UserServiceImpl();
    private final TokenService<UserVo> tokenService =
            InMemoryTokenServiceImpl.getTokenService();
    private final Map<String, Function<List<String>, Boolean>> dispatcher = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        dispatcher.put(INFO, list -> list.size() == 1 && INFO.equals(list.get(0)));
        dispatcher.put(USERNAME, list -> list.size() == 1 && USERNAME.equals(list.get(0)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(INFO).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = userInfo(token);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("user", vo);
            writer.print(result);
        }
        if (dispatcher.get(USERNAME).apply(parse)) {
            String name = req.getParameter("username");
            Boolean isAble = userService.checkUsername(name);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("isAble", isAble);
            writer.print(result);
        }
    }

    private List<String> parse(String uri) {
        String prefix = "/user/";
        String substring = uri.substring(uri.indexOf(prefix) + prefix.length());
        String[] split = substring.split("/");
        return Arrays.asList(split);
    }

    private UserVo userInfo(String token) {
        return tokenService.getData(token);
    }

}
