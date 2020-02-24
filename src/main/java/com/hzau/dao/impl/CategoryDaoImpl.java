package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.CategoryDao;
import com.hzau.domain.Category;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category";
        return queryForList(sql, Category.class);
    }
}
