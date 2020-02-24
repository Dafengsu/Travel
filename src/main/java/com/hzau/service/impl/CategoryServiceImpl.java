package com.hzau.service.impl;

import com.hzau.dao.CategoryDao;
import com.hzau.dao.impl.CategoryDaoImpl;
import com.hzau.domain.Category;
import com.hzau.service.CategoryService;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
