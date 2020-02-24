package com.hzau.service.impl;

import com.hzau.dao.UserDao;
import com.hzau.dao.impl.UserDaoImpl;
import com.hzau.domain.User;
import com.hzau.service.UserService;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            return false;
        }
        userDao.save(user);
        return true;
    }
}
