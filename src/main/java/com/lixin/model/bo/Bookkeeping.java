package com.lixin.model.bo;

import lombok.Data;

/**
 * 记账本
 *
 * @author lixin
 */
@Data
public class Bookkeeping {
    private String orderId;
    private String sellerName;
    private String buyerName;
    private String goodsName;
    private Integer price;
    private String orderTime;
}
