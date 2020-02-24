package com.hzau.service;

import com.hzau.domain.User;
import com.hzau.service.impl.UserServiceImpl;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public interface UserService {
    UserService instance = new UserServiceImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);
}
