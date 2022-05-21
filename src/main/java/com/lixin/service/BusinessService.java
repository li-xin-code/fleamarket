package com.lixin.service;

import com.lixin.model.bo.Bookkeeping;
import com.lixin.model.bo.OrderDetails;
import com.lixin.model.form.GoodsForm;
import com.lixin.model.form.ModifyGoodsForm;
import com.lixin.model.vo.GoodsVo;

import java.util.List;

/**
 * @author lixin
 */
public interface BusinessService {

    /**
     * ...
     *
     * @param page ...
     * @return ...
     */
    List<GoodsVo> goods(int page);

    /**
     * ...
     *
     * @param userId ...
     * @param page   ..
     * @return ...
     */
    List<GoodsVo> goods(String userId, int page);

    /**
     * ...
     *
     * @return ...
     */
    Integer goodsTotal();

    /**
     * ...
     *
     * @param userId ...
     * @return ...
     */
    Integer myGoodsTotal(String userId);

    /**
     * 上架商品
     *
     * @param form   ...
     * @param userId ...
     */
    void put(GoodsForm form, String userId);

    /**
     * 下架商品
     *
     * @param goodsId ...
     * @param userId  ...
     */
    void withdraw(String goodsId, String userId);

    /**
     * ...
     *
     * @param form   ...
     * @param userId ...
     */
    void modifyGoods(ModifyGoodsForm form, String userId);

    /**
     * 出售商品
     *
     * @param goodsId ...
     * @param userId  ...
     */
    void sell(String goodsId, String userId);

    /**
     * ...
     *
     * @param userId ...
     * @param flag   true: seller; false: buyer
     * @return ...
     */
    Integer orderTotal(String userId, boolean flag);

    /**
     * ...
     *
     * @param userId ...
     * @param page   ...
     * @param flag   true: seller; false: buyer
     * @return ...
     */
    List<Bookkeeping> bookkeeping(String userId, int page, boolean flag);

    /**
     * ...
     *
     * @param orderId ...
     * @param userId  ...
     * @return ...
     */
    OrderDetails oderDetails(String orderId, String userId);

    /**
     * ...
     *
     * @param orderId ...
     * @param userId  ...
     */
    void removeOrder(String orderId, String userId);

}
