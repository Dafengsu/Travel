package com.hzau.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class BaseDao {
    /**
     * sql查询一个对象，并返回封装对象
     * @param template
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public <T> T findOneObject(JdbcTemplate template, String sql, Class<T> clazz, Object... args) {
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
