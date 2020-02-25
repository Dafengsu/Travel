package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.SellerDao;
import com.hzau.domain.Seller;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class SellerDaoImpl extends BaseDao implements SellerDao {
    @Override
    public Seller findById(int sid) {
        String sql = "SELECT * FROM tab_seller WHERE sid = ?";
        return queryForObjectByRowMapper(sql, Seller.class, sid);
    }
}
