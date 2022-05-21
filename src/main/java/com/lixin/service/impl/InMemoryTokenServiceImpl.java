package com.lixin.service.impl;

import com.lixin.common.exception.InvalidTokenException;
import com.lixin.common.utils.SystemUtils;
import com.lixin.model.vo.UserVo;
import com.lixin.service.TokenService;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lixin
 */
public class InMemoryTokenServiceImpl implements TokenService<UserVo> {

    private final Map<String, UserVo> map;

    private InMemoryTokenServiceImpl() {
        this.map = new ConcurrentHashMap<>(16);
    }

    public static TokenService<UserVo> getTokenService() {
        return ClassHolder.SERVICE;
    }

    @Override
    public String getToken(UserVo data) {
        map.forEach((k, v) -> {
            if (v.equals(data)) {
                map.remove(k);
            }
        });
        String token = generateToken();
        map.put(token, data);
        return token;
    }

    @Override
    public UserVo getData(String token) {
        UserVo userVo = map.get(token);
        if (userVo == null) {
            throw new InvalidTokenException();
        }
        return userVo;
    }

    @Override
    public void removeToken(String token) {
        map.remove(token);
    }

    @Override
    public void update(String token, UserVo data) {
        map.put(token, data);
    }

    private String generateToken() {
        String random = String.valueOf((System.currentTimeMillis() +
                new Random().nextInt(8888)));
        return SystemUtils.encryptToMd5(random);
    }

    private static class ClassHolder {
        private static final TokenService<UserVo> SERVICE = new InMemoryTokenServiceImpl();
    }

}