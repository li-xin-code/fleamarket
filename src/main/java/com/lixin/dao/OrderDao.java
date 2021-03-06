package com.lixin.dao;

import com.lixin.model.bo.Bookkeeping;
import com.lixin.model.bo.OrderBo;
import com.lixin.model.bo.OrderDetails;

import java.sql.Connection;
import java.util.List;

/**
 * @author lixin
 */
public interface OrderDao {

    /**
     * ...
     *
     * @param orderBo ...
     */
    void insert(OrderBo orderBo);

    /**
     * ...
     *
     * @param orderId ...
     */
    void delete(String orderId);

    /**
     * ...
     *
     * @param orderBo    ...
     * @param connection ...
     */
    void insert(OrderBo orderBo, Connection connection);

    /***
     * ...
     *
     * @param orderId ...
     * @return ...
     */
    OrderBo find(String orderId);

    /**
     * ...
     *
     * @param userId ...
     * @param offset ...
     * @param rows   ...
     * @return ...
     */
    List<Bookkeeping> buyerOrders(String userId, int offset, int rows);

    /**
     * ...
     *
     * @param userId ...
     * @param offset ...
     * @param rows   ...
     * @return ...
     */
    List<Bookkeeping> sellerOrders(String userId, int offset, int rows);

    /**
     * ...
     *
     * @param orderId ...
     * @return ...
     */
    OrderDetails details(String orderId);

    /**
     * ...
     *
     * @param userId ...
     * @return ...
     */
    Integer buyerTotal(String userId);

    /**
     * ...
     *
     * @param userId ...
     * @return ...
     */
    Integer sellerTotal(String userId);
}
