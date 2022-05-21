package com.lixin.servlet.businsee;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.common.utils.SystemUtils;
import com.lixin.model.bo.Bookkeeping;
import com.lixin.model.bo.OrderDetails;
import com.lixin.model.vo.UserVo;
import com.lixin.service.BusinessService;
import com.lixin.service.TokenService;
import com.lixin.service.impl.BusinessServiceImpl;
import com.lixin.service.impl.InMemoryTokenServiceImpl;

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
import java.util.stream.Collectors;

/**
 * @author lixin
 */
@WebServlet(name = "orderServlet", urlPatterns = "/order/*")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = -8063933761574760981L;


    private static final String DEFAULT = "";
    private static final String DETAILS = "details";
    private static final String TOTAL = "total";
    private static final String BUYER = "buyer";
    private static final String SELLER = "seller";
    private final TokenService<UserVo> tokenService =
            InMemoryTokenServiceImpl.getTokenService();
    private final BusinessService businessService = new BusinessServiceImpl();
    private final Map<String, Function<List<String>, Boolean>> dispatcher = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        dispatcher.put(DEFAULT, list -> list.size() == 0);
        dispatcher.put(DETAILS, list -> list.size() == 2 && DETAILS.equals(list.get(0)));
        dispatcher.put(TOTAL + SELLER, list -> list.size() == 2
                && SELLER.equals(list.get(0))
                && TOTAL.equals(list.get(1)));
        dispatcher.put(TOTAL + BUYER, list -> list.size() == 2
                && BUYER.equals(list.get(0))
                && TOTAL.equals(list.get(1)));
        dispatcher.put(SELLER, list -> list.size() == 2
                && SELLER.equals(list.get(0))
                && SystemUtils.isNumber(list.get(1)));
        dispatcher.put(BUYER, list -> list.size() == 2
                && BUYER.equals(list.get(0))
                && SystemUtils.isNumber(list.get(1)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(TOTAL + SELLER).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            Integer total = businessService.orderTotal(vo.getUserId(), true);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("total", total);
            writer.print(result);
        }
        if (dispatcher.get(TOTAL + BUYER).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            Integer total = businessService.orderTotal(vo.getUserId(), false);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("total", total);
            writer.print(result);
        }
        if (dispatcher.get(SELLER).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            int page = Integer.parseInt(parse.get(1));
            List<Bookkeeping> list = businessService.bookkeeping(vo.getUserId(), page, true);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("bookkeeping", list);
            writer.print(result);
            return;
        }
        if (dispatcher.get(BUYER).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            int page = Integer.parseInt(parse.get(1));
            List<Bookkeeping> list = businessService.bookkeeping(vo.getUserId(), page, false);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("bookkeeping", list);
            writer.print(result);
            return;
        }
        if (dispatcher.get(DETAILS).apply(parse)) {
            String token = req.getHeader("token");
            if (token == null) {
                return;
            }
            UserVo vo = tokenService.getData(token);
            String orderId = parse.get(1);
            OrderDetails details = businessService.oderDetails(orderId, vo.getUserId());
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("details", details);
            writer.print(result);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(DEFAULT).apply(parse)) {
            String token = req.getHeader("token");
            if (token == null) {
                return;
            }
            UserVo vo = tokenService.getData(token);
            String orderId = (String) req.getAttribute("orderId");
            businessService.removeOrder(orderId, vo.getUserId());
        }
    }

    private List<String> parse(String uri) {
        String prefix = "/order";
        String substring = uri.substring(uri.indexOf(prefix) + prefix.length());
        String[] split = substring.split("/");
        return Arrays.stream(split).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
}
