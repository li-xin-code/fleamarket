package com.lixin.dao;


import com.lixin.model.bo.UserBo;

import java.util.List;

/**
 * @author lixin
 */
public interface UserDao {

    /**
     * find user by username
     *
     * @param username username
     * @return user
     */
    UserBo find(String username);

    /**
     * find user by userId
     *
     * @param userId username
     * @return user
     */
    UserBo findByUserId(String userId);

    /**
     * add user
     *
     * @param userBo user
     * @return succeed or not
     */
    Boolean insert(UserBo userBo);

    /**
     * ...
     *
     * @param userBo ...
     * @return ...
     */
    Boolean update(UserBo userBo);

    /**
     * all users
     *
     * @return list
     */
    List<UserBo> list();

    /**
     * Does the username exist.
     *
     * @param username ...
     * @return ...
     */
    Boolean isExist(String username);

}
