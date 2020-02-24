package com.hzau.dao;

import com.hzau.domain.Category;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public interface CategoryDao{
    /**
     * 查询所有
     * @return
     */
    List<Category> findAll();
}
