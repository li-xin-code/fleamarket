package com.lixin.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
public class UserVo {
    private String userId;
    private String username;
}
