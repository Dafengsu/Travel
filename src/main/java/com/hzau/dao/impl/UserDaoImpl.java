package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.UserDao;
import com.hzau.domain.User;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User findByUsername(String username) {
        return queryForObject(
                "SELECT * FROM tab_user where username = ?",
                User.class, username);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user " +
                "(username, password, name, birthday, sex, telephone,email,code,status) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        update(sql, user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),
                user.getCode(), user.getStatus());
    }

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {

        return queryForObject(
                "SELECT * FROM tab_user WHERE code = ?",
                User.class, code);
    }

    @Override
    public void updateStatus(User user) {
        String sql = "UPDATE tab_user SET status = 'Y' WHERE uid = ?";
        update(sql, user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return queryForObject(
                "SELECT * from tab_user WHERE username = ? AND password = ?",
                User.class, username, password);
    }
}
