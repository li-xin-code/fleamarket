package com.lixin.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author com.lixin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

    private String username;
    private String password;
    private String repeatPassword;

}
