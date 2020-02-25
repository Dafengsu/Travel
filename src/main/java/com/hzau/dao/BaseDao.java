package com.hzau.dao;

import com.hzau.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
    public <T> T queryForObjectByRowMapper(String sql, Class<T> clazz, Object... args) {
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public <T> T queryForObjectByRequireType(String sql, Class<T> clazz, Object... args) {
        try {
            return template.queryForObject(sql, clazz, args);
        } catch (DataAccessException e) {
            return null;
        }
    }
    /**
     * 查询多个对象，以list形式返回
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(String sql, Class<T> clazz, Object... args) {
        if (args == null) {
            return template.query(sql, new BeanPropertyRowMapper<>(clazz));
        }
        return template.query(sql, new BeanPropertyRowMapper<>(clazz), args);
    }
    /**
     * 更新
     * @param sql
     * @param args
     */
    public void update(String sql, Object... args) {
        template.update(sql, args);
    }
}
