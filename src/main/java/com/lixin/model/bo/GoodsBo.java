package com.lixin.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author com.lixin
 */
@Data
public class GoodsBo {
    private String goodsId;
    private String sellerId;
    private String name;
    private String description;
    private Integer price;
    private LocalDateTime createTime;
    private Integer remove;
}
