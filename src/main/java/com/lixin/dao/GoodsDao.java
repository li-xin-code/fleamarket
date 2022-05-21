package com.lixin.dao;


import com.lixin.model.bo.GoodsBo;

import java.sql.Connection;
import java.util.List;

/**
 * @author lixin
 */
public interface GoodsDao {

    /**
     * ...
     *
     * @param goodsBo ...
     */
    void insert(GoodsBo goodsBo);

    /**
     * ...
     *
     * @param offset ...
     * @param row    ...
     * @return ...
     */
    List<GoodsBo> list(int offset, int row);

    /**
     * ...
     *
     * @param userId ...
     * @param offset ...
     * @param row    ...
     * @return ...
     */
    List<GoodsBo> findByUserId(String userId, int offset, int row);

    /**
     * ...
     *
     * @return ...
     */
    Integer total();

    /**
     * ...
     *
     * @param userId ..
     * @return ...
     */
    Integer total(String userId);

    /**
     * ...
     *
     * @param goodsId ...
     * @return ...
     */
    GoodsBo find(String goodsId);

    /**
     * ...
     *
     * @param goodsId ...
     */
    void delete(String goodsId);

    /**
     * ...
     *
     * @param goodsId    ...
     * @param connection ...
     */
    void delete(String goodsId, Connection connection);

    /**
     * ...
     *
     * @param goodsBo ...
     */
    void update(GoodsBo goodsBo);

    /**
     * ...
     *
     * @param goodsId ...
     * @return ...
     */
    Boolean isExist(String goodsId);


}
