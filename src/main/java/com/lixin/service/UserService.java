package com.lixin.service;

import com.lixin.common.exception.AuthenticateException;
import com.lixin.model.vo.UserVo;


/**
 * @author lixin
 */
public interface UserService {

    /**
     * ...
     *
     * @param username ...
     * @param password ...
     * @return ...
     * @throws AuthenticateException ...
     */
    UserVo login(String username, String password) throws AuthenticateException;

    /**
     * ...
     *
     * @param username ...
     * @param password ...
     * @return ...
     */
    UserVo register(String username, String password);

    /**
     * check if username is available
     *
     * @param username username
     * @return true or false
     */
    Boolean checkUsername(String username);

}
