package com.hzau.service;

import com.hzau.domain.User;
import com.hzau.service.impl.UserServiceImpl;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    boolean active(String code);

    User login(User user);
}
