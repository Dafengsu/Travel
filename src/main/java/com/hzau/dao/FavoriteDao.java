package com.hzau.dao;

import com.hzau.domain.Favorite;

/**
 * @author su
 * @description
 * @date 2020/2/26
 */
public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
