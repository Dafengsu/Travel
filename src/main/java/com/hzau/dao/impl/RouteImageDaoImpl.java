package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.RouteImageDao;
import com.hzau.domain.RouteImg;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class RouteImageDaoImpl extends BaseDao implements RouteImageDao {
    @Override
    public List<RouteImg> findById(int rid) {
        String sql = "SELECT * FROM tab_route_img WHERE rid = ?";
        return queryForList(sql, RouteImg.class, rid);
    }
}
