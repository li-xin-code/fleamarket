package com.lixin.servlet.businsee;

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

/**
 * @author lixin
 */
@WebServlet(name = "sellServlet", urlPatterns = "/sell")
public class SellServlet extends HttpServlet {

    private static final long serialVersionUID = -3981846858011396821L;

    private final TokenService<UserVo> tokenService =
            InMemoryTokenServiceImpl.getTokenService();
    private final BusinessService businessService = new BusinessServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        if (token == null) {
            return;
        }
        UserVo vo = tokenService.getData(token);
        String goodsId = (String) req.getAttribute("goodsId");
        businessService.sell(goodsId, vo.getUserId());
    }
}
