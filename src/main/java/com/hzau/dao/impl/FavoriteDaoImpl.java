package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.FavoriteDao;
import com.hzau.domain.Favorite;

import java.util.Date;

/**
 * @author su
 * @description
 * @date 2020/2/26
 */
public class FavoriteDaoImpl extends BaseDao implements FavoriteDao {
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        String sql = "SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";
        return queryForObjectByRowMapper(sql, Favorite.class, rid, uid);
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "SELECT COUNT(*) from tab_favorite WHERE rid = ?";
        return queryForObjectByRequireType(sql, Integer.class, rid);
    }

    @Override
    public void add(String rid, int uid) {
        String sql = "INSERT INTO tab_favorite VALUES (?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }
}
