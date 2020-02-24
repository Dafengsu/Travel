package com.hzau.dao;

import com.hzau.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class BaseDao {
    protected JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * sql查询一个对象，并返回封装对象
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public <T> T findOneObject(String sql, Class<T> clazz, Object... args) {
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
