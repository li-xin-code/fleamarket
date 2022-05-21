package com.lixin.servlet.businsee;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.SystemUtils;
import com.lixin.model.form.GoodsForm;
import com.lixin.model.form.ModifyGoodsForm;
import com.lixin.model.vo.GoodsVo;
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
@WebServlet(name = "goodsServlet", urlPatterns = "/goods/*")
public class GoodsServlet extends HttpServlet {

    private static final long serialVersionUID = 6123437304794125947L;
    private static final String DEFAULT = "";
    private static final String MY_PAGE = "my";
    private static final String ALL_PAGE = "all_page";
    private static final String TOTAL = "total";
    private static final String MY_TOTAL = "my-total";
    private final TokenService<UserVo> tokenService =
            InMemoryTokenServiceImpl.getTokenService();
    private final BusinessService businessService = new BusinessServiceImpl();
    private final Map<String, Function<List<String>, Boolean>> dispatcher = new HashMap<>();


    @Override
    public void init() throws ServletException {
        super.init();
        dispatcher.put(DEFAULT, list -> list.size() == 0);
        dispatcher.put(ALL_PAGE, list -> list.size() == 1
                && SystemUtils.isNumber(list.get(0)));
        dispatcher.put(MY_PAGE, list -> list.size() == 2
                && MY_PAGE.equals(list.get(0))
                && SystemUtils.isNumber(list.get(1)));
        dispatcher.put(TOTAL, list -> list.size() == 1
                && TOTAL.equals(list.get(0)));
        dispatcher.put(MY_TOTAL, list -> list.size() == 1
                && MY_TOTAL.equals(list.get(0)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(TOTAL).apply(parse)) {
            Integer total = businessService.goodsTotal();
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("total", total);
            writer.print(result);
        }
        if (dispatcher.get(ALL_PAGE).apply(parse)) {
            int page;
            try {
                page = Integer.parseInt(parse.get(0));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new NotExpectedException("page parse fail.");
            }
            List<GoodsVo> goods = businessService.goods(page);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("goods", goods);
            writer.print(result);
        }
        if (dispatcher.get(MY_PAGE).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            int page;
            try {
                page = Integer.parseInt(parse.get(1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new NotExpectedException("page parse fail.");
            }
            List<GoodsVo> goods = businessService.goods(vo.getUserId(), page);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("goods", goods);
            writer.print(result);
        }
        if (dispatcher.get(MY_TOTAL).apply(parse)) {
            String token = req.getHeader("token");
            UserVo vo = tokenService.getData(token);
            Integer total = businessService.myGoodsTotal(vo.getUserId());
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            JSONObject result = new JSONObject();
            result.put("total", total);
            writer.print(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(DEFAULT).apply(parse)) {
            String token = req.getHeader("token");
            UserVo userVo = tokenService.getData(token);
            String name = (String) req.getAttribute("name");
            String description = (String) req.getAttribute("description");
            Integer price = Integer.parseInt((String) req.getAttribute("price"));
            GoodsForm goodsForm = new GoodsForm();
            goodsForm.setName(name)
                    .setDescription(description)
                    .setPrice(price);
            System.out.println(goodsForm);
            businessService.put(goodsForm, userVo.getUserId());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(DEFAULT).apply(parse)) {
            String token = req.getHeader("token");
            UserVo userVo = tokenService.getData(token);
            String goodsId = (String) req.getAttribute("goodsId");
            String name = (String) req.getAttribute("name");
            String description = (String) req.getAttribute("description");
            Integer price = Integer.parseInt((String) req.getAttribute("price"));
            ModifyGoodsForm form = new ModifyGoodsForm();
            form.setName(name)
                    .setGoodsId(goodsId)
                    .setDescription(description)
                    .setPrice(price);
            System.out.println(form);
            businessService.modifyGoods(form, userVo.getUserId());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        String token = req.getHeader("token");
        if (token == null) {
            return;
        }
        UserVo userVo = tokenService.getData(token);
        String goodsId = (String) req.getAttribute("goodsId");
        businessService.withdraw(goodsId, userVo.getUserId());
    }

    private List<String> parse(String uri) {
        String prefix = "/goods";
        String substring = uri.substring(uri.indexOf(prefix) + prefix.length());
        String[] split = substring.split("/");
        return Arrays.stream(split).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

}
