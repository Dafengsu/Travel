package com.hzau.service.impl;

import com.hzau.dao.FavoriteDao;
import com.hzau.dao.RouteDao;
import com.hzau.dao.RouteImageDao;
import com.hzau.dao.SellerDao;
import com.hzau.dao.impl.FavoriteDaoImpl;
import com.hzau.dao.impl.RouteDaoImpl;
import com.hzau.dao.impl.RouteImageDaoImpl;
import com.hzau.dao.impl.SellerDaoImpl;
import com.hzau.domain.PageBean;
import com.hzau.domain.Route;
import com.hzau.domain.RouteImg;
import com.hzau.domain.Seller;
import com.hzau.service.RouterService;

import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class RouterServiceImpl implements RouterService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao routeImageDao = new RouteImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<>();
        pb.setPageSize(pageSize);
        pb.setCurrentPage(currentPage);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        //设置当前显示的数据集合
        pb.setList(routeDao.findByPage(cid,
                (currentPage - 1) * pageSize, pageSize,rname));
        pb.setTotalPage(totalCount % pageSize == 0
                ? totalCount / pageSize : totalCount / pageSize + 1);
        return pb;
    }

    @Override
    public Route findOne(int rid) {

        //1.根据id查询route对象
        Route route = routeDao.findOne(rid);
        //2.根据id查询路线的图片集合
        List<RouteImg> routeImageList = routeImageDao.findById(rid);
        route.setRouteImgList(routeImageList);
        //3.查询卖家
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //4.查询收藏次数
        int count = favoriteDao.findCountByRid(rid);
        route.setCount(count);
        return route;
    }
}
