package com.lixin.dao.impl;

import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.dbutils.DbUtils;
import com.lixin.common.utils.dbutils.GenerousBeanHandler;
import com.lixin.common.utils.dbutils.GenerousBeanListHandler;
import com.lixin.dao.UserDao;
import com.lixin.model.bo.UserBo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 */
public class UserDaoImpl implements UserDao {

    private final QueryRunner runner = DbUtils.getRunner();

    private UserDaoImpl() {
    }

    public static UserDao getUserDao() {
        return UserDaoHolder.USER_DAO;
    }

    @Override
    public UserBo find(String username) {
        String sql = "select user_id, username, password from user where username = ?";
        try {
            return runner.query(sql, new GenerousBeanHandler<>(UserBo.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserBo findByUserId(String userId) {
        String sql = "select user_id,username,password from user where user_id = ?";
        try {
            return runner.query(sql, new GenerousBeanHandler<>(UserBo.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean insert(UserBo userBo) {
        String sql = "insert into user(user_id,username,password) value (?,?,?)";
        try {
            int rows = runner.update(sql, userBo.getUserId(),
                    userBo.getUsername(), userBo.getPassword());
            if (rows != 1) {
                throw new NotExpectedException(sql + ";result not 1.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(UserBo userBo) {
        if (userBo == null) {
            return false;
        }
        String userId = userBo.getUserId();
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        UserBo byUserBoId = findByUserId(userId);
        String sql = "update user set username = ?, password = ? where user_id = ?";
        try {
            String newName = userBo.getUsername();
            String newPass = userBo.getPassword();
            String username = newName != null ? newName : byUserBoId.getUsername();
            String pass = newPass != null ? newPass : byUserBoId.getPassword();
            runner.update(sql, username, pass, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<UserBo> list() {
        String sql = "select user_id,username,password from user";
        try {
            return runner.query(sql, new GenerousBeanListHandler<>(UserBo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean isExist(String username) {
        String sql = "select count(*) from user where username = ?";
        try {
            Long exist = runner.query(sql, new ScalarHandler<>(), username);
            return exist > 0L;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static final class UserDaoHolder {
        static final UserDao USER_DAO = new UserDaoImpl();
    }

}
