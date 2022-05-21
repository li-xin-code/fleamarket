package com.lixin.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lixin
 */
@Data
public class OrderBo {
    private String orderId;
    private String goodsId;
    private String buyerId;
    private String sellerId;
    private LocalDateTime createTime;
    private Integer remove;
}
