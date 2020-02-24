package com.hzau.service.impl;

import com.hzau.dao.CategoryDao;
import com.hzau.dao.impl.CategoryDaoImpl;
import com.hzau.domain.Category;
import com.hzau.service.CategoryService;
import com.hzau.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> tuples = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        if (tuples == null || tuples.size() == 0) {
            cs = categoryDao.findAll();
            for (Category category : cs) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
        } else {
            cs = new ArrayList<>();
            for (Tuple tuple : tuples) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }

}
