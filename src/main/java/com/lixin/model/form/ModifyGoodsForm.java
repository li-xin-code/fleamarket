package com.lixin.model.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
public class ModifyGoodsForm {
    private String goodsId;
    private String name;
    private String description;
    private Integer price;
}
