package com.hzau.dao.impl;

import com.hzau.dao.UserDao;
import com.hzau.domain.User;
import com.hzau.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM tab_user where username = ?";
            return template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user " +
                "(username, password, name, birthday, sex, telephone,email) " +
                "VALUES (?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(),
                user.getName(), user.getBirthday(), user.getSex(),
                user.getTelephone(), user.getEmail());
    }
}
