package com.lixin.service.impl;

import com.lixin.common.exception.AuthenticateException;
import com.lixin.common.exception.NotExpectedException;
import com.lixin.common.utils.SystemUtils;
import com.lixin.dao.UserDao;
import com.lixin.dao.impl.UserDaoImpl;
import com.lixin.model.bo.UserBo;
import com.lixin.model.form.ModifyUerInfoForm;
import com.lixin.model.vo.UserVo;
import com.lixin.service.UserService;

import java.util.regex.Pattern;


/**
 * @author lixin
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = UserDaoImpl.getUserDao();

    @Override
    public UserVo login(String username, String password) throws AuthenticateException {
        UserBo userBo = userDao.find(username);
        if (userBo == null || userBo.equals(new UserBo())) {
            throw new AuthenticateException("username wrong.");
        }
        if (!userBo.getPassword().equals(SystemUtils.encryptToMd5(password))) {
            throw new AuthenticateException("password wrong.");
        }
        return b2v(userBo);
    }

    @Override
    public UserVo register(String username, String password) {
        UserBo userBo = new UserBo();
        if (!Pattern.matches(SystemUtils.USERNAME_REGEX, username)) {
            throw new NotExpectedException("username format error.");
        }
        if (!Pattern.matches(SystemUtils.PASSWORD_REGEX, password)) {
            throw new NotExpectedException("password format error.");
        }
        if (userDao.isExist(username)) {
            throw new NotExpectedException("username already exists.");
        }
        userBo.setUserId(SystemUtils.uuid());
        userBo.setUsername(username);
        userBo.setPassword(SystemUtils.encryptToMd5(password));
        userDao.insert(userBo);
        return b2v(userBo);
    }

    @Override
    public Boolean checkUsername(String username) {
        return !userDao.isExist(username);
    }

    @Override
    public UserVo get(String userId) {
        return b2v(userDao.findByUserId(userId));
    }

    @Override
    public UserVo modifyUserInfo(ModifyUerInfoForm form, String userId) {
        UserBo userBo = new UserBo();
        userBo.setUserId(userId);
        String name = form.getName();
        if (name.length() > 0) {
            if (!Pattern.matches(SystemUtils.USERNAME_REGEX, name)) {
                throw new NotExpectedException("username format error.");
            }
            if (userDao.isExist(name)) {
                throw new NotExpectedException("username already exists.");
            }
        }
        String pass = form.getPass();
        if (pass.length() > 0) {
            if (!Pattern.matches(SystemUtils.PASSWORD_REGEX, pass)) {
                throw new NotExpectedException("password format error.");
            }
            pass = SystemUtils.encryptToMd5(pass);
        }
        userBo.setUsername(name);
        userBo.setPassword(pass);
        userBo.setProfile(form.getProfile());
        userDao.update(userBo);
        return b2v(userDao.findByUserId(userId));
    }

    private UserVo b2v(UserBo bo) {
        UserVo vo = new UserVo();
        return vo.setUsername(bo.getUsername())
                .setUserId(bo.getUserId())
                .setProfile(bo.getProfile());
    }
}
