package com.lixin.model.bo;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class OrderDetails {
    private String orderId;
    private String goodsId;
    private String buyerId;
    private String sellerId;
    private String sellerName;
    private String buyerName;
    private String goodsName;
    private String description;
    private Integer price;
    private String goodsTime;
    private String orderTime;
}
