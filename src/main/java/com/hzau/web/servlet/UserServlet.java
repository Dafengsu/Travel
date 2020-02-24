package com.hzau.web.servlet;

import com.hzau.domain.ResultInfo;
import com.hzau.domain.User;
import com.hzau.service.UserService;
import com.hzau.service.impl.UserServiceImpl;
import com.hzau.util.LocalUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter("check");
        //从session获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //准备返回数据对象
        ResultInfo info = new ResultInfo();
        if (check.equalsIgnoreCase(checkcode_server)) {
            //获取数据，封装对象
            User user = LocalUtils.beanUserByReq(request, User.class);
            //调用service,响应结果
            if (service.register(user)) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        } else {
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        }
        //将对象转化为Json并写回客户端
        LocalUtils.sendObjectAsJson(info, response);
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将用户名和密码封装成User对象
        User user = LocalUtils.beanUserByReq(request, User.class);
        //调用service查询
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else if (!"Y".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活,请登录邮箱激活");
        } else {
            info.setFlag(true);
            request.getSession().setAttribute("user", u);
        }

        LocalUtils.sendObjectAsJson(info, response);
    }

    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        LocalUtils.sendObjectAsJson(user, response);
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            String msg = service.active(code) ?
                    "激活成功，请<a href='login.html'>登录</a>"
                    : "激活失败，请联系管理员！";
            response.getWriter().write(msg);
        }
    }
}
