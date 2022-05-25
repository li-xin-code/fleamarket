package com.lixin.model.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
public class ModifyUerInfoForm {
    private String name;
    private String pass;
    private String profile;
}
