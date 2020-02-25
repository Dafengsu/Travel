package com.hzau.service;

import com.hzau.domain.PageBean;
import com.hzau.domain.Route;

/**
 * @author su
 * @description     线路service
 * @date 2020/2/25
 */
public interface RouterService {
    /**
     * 进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(int rid);
}
