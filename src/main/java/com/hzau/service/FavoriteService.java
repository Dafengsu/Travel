package com.hzau.service;

/**
 * @author su
 * @description
 * @date 2020/2/26
 */
public interface FavoriteService {
    boolean isFavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
