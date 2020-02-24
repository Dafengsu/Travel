package com.hzau.web.servlet;

import com.hzau.domain.ResultInfo;
import com.hzau.domain.User;
import com.hzau.service.UserService;
import com.hzau.util.LocalBeanUtils;
import com.hzau.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            User user = new User();
            LocalBeanUtils.beanUserByReq(request, user);
            //调用service,响应结果
            UserService service = UserService.instance;

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
        //序列化对象为json
        String json = JsonUtil.writeValueAsString(info);
        //将数据写回客户端
        JsonUtil.sendJsonToClient(response, json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
