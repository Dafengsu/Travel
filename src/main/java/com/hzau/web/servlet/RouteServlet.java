package com.hzau.web.servlet;

import com.hzau.domain.PageBean;
import com.hzau.domain.Route;
import com.hzau.domain.User;
import com.hzau.service.FavoriteService;
import com.hzau.service.RouterService;
import com.hzau.service.impl.FavoriteServiceImpl;
import com.hzau.service.impl.RouterServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouterService service = new RouterServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        int cid = 0;
        if (cidStr != null && cidStr.length() != 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() != 0 && !"null".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() != 0 && !"null".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        /*if (rname == null || rname.length() == 0 || rname.equals("null")) {

        }*/
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);
        sendObjectAsJson(pb, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接收id
        String rid = request.getParameter("rid");
        Route route = service.findOne(Integer.parseInt(rid));
        sendObjectAsJson(route, response);
    }

    /**
     * 判断当前登录用户是否收藏过该路线
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        boolean flag = false;
        if (user != null) {
            int uid = user.getUid();
            String rid = request.getParameter("rid");
            flag = favoriteService.isFavorite(Integer.parseInt(rid), uid);
        }
        sendObjectAsJson(flag, response);
    }
    /**
     * 添加收藏
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return;
        }
        favoriteService.add(rid, user.getUid());
    }
}
