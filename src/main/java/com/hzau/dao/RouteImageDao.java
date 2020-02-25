package com.hzau.dao;

import com.hzau.domain.RouteImg;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public interface RouteImageDao {
    /**
     * 根据线路的id查询图片
     * @param rid
     * @return
     */
    List<RouteImg> findById(int rid);
}
