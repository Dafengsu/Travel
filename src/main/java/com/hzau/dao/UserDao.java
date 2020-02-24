package com.hzau.dao;

import com.hzau.domain.User;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户添加
     * @param user
     */
    void save(User user);
}
