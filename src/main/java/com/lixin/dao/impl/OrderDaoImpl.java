package com.lixin.dao.impl;

import com.lixin.common.utils.dbutils.DbUtils;
import com.lixin.common.utils.dbutils.GenerousBeanHandler;
import com.lixin.common.utils.dbutils.GenerousBeanListHandler;
import com.lixin.dao.OrderDao;
import com.lixin.model.bo.Bookkeeping;
import com.lixin.model.bo.OrderBo;
import com.lixin.model.bo.OrderDetails;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lixin
 */
public class OrderDaoImpl implements OrderDao {

    private final QueryRunner runner = DbUtils.getRunner();

    private OrderDaoImpl() {
    }

    public static OrderDao getOrderDao() {
        return Holder.ORDER_DAO;
    }

    @Override
    public void insert(OrderBo orderBo) {
        String sql = "insert into `order`(order_id, goods_id, buyer_id, seller_id, create_time, remove) " +
                "value (?,?,?,?,?,0)";
        try {
            runner.update(sql, orderBo.getOrderId(), orderBo.getGoodsId(),
                    orderBo.getBuyerId(), orderBo.getSellerId(), LocalDateTime.now());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String orderId) {
        String sql = "update `order` set remove = 1 where order_id = ?";
        try {
            runner.update(sql, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(OrderBo orderBo, Connection connection) {
        String sql = "insert into " +
                "`order`(order_id, goods_id, buyer_id, seller_id, create_time, remove) " +
                "value (?,?,?,?,?,0)";
        try {
            runner.update(connection, sql, orderBo.getOrderId(), orderBo.getGoodsId(),
                    orderBo.getBuyerId(), orderBo.getSellerId(), LocalDateTime.now());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderBo find(String orderId) {
        String sql = "select order_id, goods_id, buyer_id, seller_id,create_time" +
                " from `order` where remove = 0 and order_id = ?";
        try {
            return runner.query(sql, new GenerousBeanHandler<>(OrderBo.class), orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bookkeeping> buyerOrders(String userId, int offset, int rows) {
        String sql = "select o.order_id, u.username seller_name," +
                "(select username from user where user_id = o.buyer_id) buyer_name, " +
                "g.name goods_name, g.price, o.create_time order_time " +
                "from `order` o join goods g on g.goods_id = o.goods_id " +
                "join user u on g.seller_id = u.user_id " +
                "where o.buyer_id = ? and o.remove = 0 " +
                "limit ?,?";
        try {
            return runner.query(sql, new GenerousBeanListHandler<>(Bookkeeping.class),
                    userId, offset, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bookkeeping> sellerOrders(String userId, int offset, int rows) {
        String sql = "select o.order_id, u.username seller_name," +
                "(select username from user where user_id = o.buyer_id) buyer_name, " +
                "g.name goods_name, g.price, o.create_time order_time " +
                "from `order` o join goods g on g.goods_id = o.goods_id " +
                "join user u on g.seller_id = u.user_id " +
                "where o.seller_id = ? " +
                "limit ?,?";
        try {
            return runner.query(sql, new GenerousBeanListHandler<>(Bookkeeping.class),
                    userId, offset, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDetails details(String orderId) {
        String sql = "select o.order_id, o.goods_id,o.buyer_id, g.seller_id,u.username seller_name," +
                "(select username from user where user_id = o.buyer_id) buyer_name," +
                "g.name goods_name, g.description ,g.price, " +
                "g.create_time goods_time,o.create_time order_time " +
                "from `order` o join goods g on g.goods_id = o.goods_id " +
                "join user u on g.seller_id = u.user_id " +
                "where o.remove = 0 and order_id = ?";
        try {
            return runner.query(sql, new GenerousBeanHandler<>(OrderDetails.class), orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer buyerTotal(String userId) {
        String sql = "select count(*) from `order` o " +
                "where o.buyer_id = ? and o.remove = 0";
        try {
            Long rows = runner.query(sql, new ScalarHandler<>(), userId);
            return rows.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer sellerTotal(String userId) {
        String sql = "select count(*) from `order` o " +
                "where o.seller_id = ?";
        try {
            Long rows = runner.query(sql, new ScalarHandler<>(), userId);
            return rows.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Holder {
        private static final OrderDao ORDER_DAO = new OrderDaoImpl();
    }
}
