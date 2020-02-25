package com.hzau.service.impl;

import com.hzau.dao.FavoriteDao;
import com.hzau.dao.impl.FavoriteDaoImpl;
import com.hzau.domain.Favorite;
import com.hzau.service.FavoriteService;

/**
 * @author su
 * @description
 * @date 2020/2/26
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(rid, uid);
    }
}
