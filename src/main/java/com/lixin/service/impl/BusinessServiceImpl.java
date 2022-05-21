package com.lixin.service.impl;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.SystemUtils;
import com.lixin.common.utils.dbutils.DbUtils;
import com.lixin.dao.GoodsDao;
import com.lixin.dao.OrderDao;
import com.lixin.dao.impl.GoodsDaoImpl;
import com.lixin.dao.impl.OrderDaoImpl;
import com.lixin.model.bo.Bookkeeping;
import com.lixin.model.bo.GoodsBo;
import com.lixin.model.bo.OrderBo;
import com.lixin.model.bo.OrderDetails;
import com.lixin.model.form.GoodsForm;
import com.lixin.model.form.ModifyGoodsForm;
import com.lixin.model.vo.GoodsVo;
import com.lixin.service.BusinessService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 */
public class BusinessServiceImpl implements BusinessService {

    private final GoodsDao goodsDao = GoodsDaoImpl.getGoodsDao();
    private final OrderDao orderDao = OrderDaoImpl.getOrderDao();
    private final int ROWS = 10;

    @Override
    public List<GoodsVo> goods(int page) {
        int offset = page < 2 ? 0 : (page - 1) * ROWS;
        List<GoodsBo> goods = goodsDao.list(offset, ROWS);
        List<GoodsVo> list = new ArrayList<>();
        goods.forEach(g -> list.add(b2v(g)));
        return list;
    }

    @Override
    public List<GoodsVo> goods(String userId, int page) {
        int offset = page < 2 ? 0 : (page - 1) * ROWS;
        List<GoodsBo> goods = goodsDao.findByUserId(userId, offset, ROWS);
        List<GoodsVo> list = new ArrayList<>();
        goods.forEach(g -> list.add(b2v(g)));
        return list;
    }

    @Override
    public Integer goodsTotal() {
        return goodsDao.total();
    }

    @Override
    public Integer myGoodsTotal(String userId) {
        return goodsDao.total(userId);
    }

    @Override
    public void put(GoodsForm form, String userId) {
        GoodsBo goodsBo = new GoodsBo();
        goodsBo.setGoodsId(SystemUtils.uuid());
        goodsBo.setSellerId(userId);
        goodsBo.setName(form.getName());
        goodsBo.setDescription(form.getDescription());
        goodsBo.setPrice(form.getPrice());
        goodsDao.insert(goodsBo);
    }

    @Override
    public void withdraw(String goodsId, String userId) {
        GoodsBo goodsBo = goodsDao.find(goodsId);
        if (!goodsBo.getSellerId().equals(userId)) {
            return;
        }
        goodsDao.delete(goodsId);
    }

    @Override
    public void modifyGoods(ModifyGoodsForm form, String userId) {
        GoodsBo goodsBo = goodsDao.find(form.getGoodsId());
        if (!goodsBo.getSellerId().equals(userId)) {
            return;
        }
        goodsBo.setGoodsId(form.getGoodsId());
        goodsBo.setName(form.getName());
        goodsBo.setDescription(form.getDescription());
        goodsBo.setPrice(form.getPrice());
        goodsDao.update(goodsBo);
    }

    @Override
    public void sell(String goodsId, String userId) {
        if (!goodsDao.isExist(goodsId)) {
            throw new NotExpectedException("goodsId is not exist.");
        }
        GoodsBo goodsBo = goodsDao.find(goodsId);
        OrderBo orderBo = new OrderBo();
        orderBo.setOrderId(SystemUtils.uuid());
        orderBo.setGoodsId(goodsId);
        orderBo.setBuyerId(userId);
        orderBo.setSellerId(goodsBo.getSellerId());
        try {
            Connection connection = DbUtils.getDataSource().getConnection();
            connection.setAutoCommit(false);
            orderDao.insert(orderBo, connection);
            goodsDao.delete(goodsId, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Integer orderTotal(String userId, boolean flag) {
        return flag ? orderDao.sellerTotal(userId) : orderDao.buyerTotal(userId);
    }

    @Override
    public List<Bookkeeping> bookkeeping(String userId, int page, boolean flag) {
        int offset = page < 2 ? 0 : (page - 1) * ROWS;
        return flag ? orderDao.sellerOrders(userId, offset, ROWS)
                : orderDao.buyerOrders(userId, offset, ROWS);
    }

    @Override
    public OrderDetails oderDetails(String orderId, String userId) {
        OrderBo bo = orderDao.find(orderId);
        if (bo.getBuyerId().equals(userId) || bo.getSellerId().equals(userId)) {
            return orderDao.details(orderId);
        }
        return null;
    }

    @Override
    public void removeOrder(String orderId, String userId) {
        OrderBo orderBo = orderDao.find(orderId);
        if (!orderBo.getBuyerId().equals(userId)) {
            return;
        }
        orderDao.delete(orderId);
    }

    private GoodsVo b2v(GoodsBo bo) {
        GoodsVo vo = new GoodsVo();
        vo.setGoodsId(bo.getGoodsId())
                .setSellerId(bo.getSellerId())
                .setName(bo.getName())
                .setDescription(bo.getDescription())
                .setPrice(bo.getPrice())
                .setCreateTime(SystemUtils.dateFormat(bo.getCreateTime()));
        return vo;
    }

}
