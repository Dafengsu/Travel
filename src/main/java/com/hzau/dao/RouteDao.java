package com.hzau.dao;

import com.hzau.domain.Route;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid,start,pageSize查询当前页的集合
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(int rid);
}
