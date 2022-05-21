package com.lixin.model.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
public class GoodsForm {
    private String name;
    private String description;
    private Integer price;
}
