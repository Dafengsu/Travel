package com.hzau.dao.impl;

import com.hzau.dao.BaseDao;
import com.hzau.dao.RouteDao;
import com.hzau.domain.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class RouteDaoImpl extends BaseDao implements RouteDao {
    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        processString(cid, rname, params, sb);
        sql = sb.toString();
        return queryForObjectByRequireType(sql, Integer.class, params.toArray());
    }
    
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "SELECT * FROM tab_route WHERE 1 = 1";
        List<Object> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder(sql);
        processString(cid, rname, params, sb);
        sb.append(" LIMIT ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        return queryForList(sql, Route.class, params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "SELECT * FROM tab_route WHERE rid = ?";
        return queryForObjectByRowMapper(sql, Route.class, rid);
    }

    private void processString(int cid, String rname, List<Object> params, StringBuilder sb) {
        if (cid != 0) {
            sb.append(" AND cid = ?");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" AND rname LIKE ?");
            params.add("%" + rname + "%");
        }
    }

}
