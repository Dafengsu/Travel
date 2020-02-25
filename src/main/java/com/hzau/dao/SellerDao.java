package com.hzau.dao;

import com.hzau.domain.Seller;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public interface SellerDao {
    Seller findById(int sid);
}
