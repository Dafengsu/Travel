package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.CategoryDao;
import com.hzau.domain.Category;
import com.hzau.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category";
        return template.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
