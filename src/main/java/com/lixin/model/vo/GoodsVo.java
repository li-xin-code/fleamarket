package com.lixin.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
public class GoodsVo {
    private String goodsId;
    private String sellerId;
    private String name;
    private String description;
    private Integer price;
    private String createTime;
}
