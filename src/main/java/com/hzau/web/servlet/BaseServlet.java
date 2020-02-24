package com.hzau.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */

public class BaseServlet extends HttpServlet {




    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * Json转换工具类
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 把对象以JSON的形式发送到response
     *
     * @param value
     * @param response
     * @throws IOException
     */
    public void sendObjectAsJson(Object value, ServletResponse response) throws IOException {
        sendJsonToClient(response, writeValueAsString(value));
    }

    /**
     * 对象转JSON
     *
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    /**
     * 发送JSON数据到response
     *
     * @param response
     * @param json
     * @throws IOException
     */
    public void sendJsonToClient(ServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }


    /**
     * 把请求数据封装成bean
     *
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T beanUserByReq(HttpServletRequest request, Class<T> clazz) {
        Map<String, String[]> map = request.getParameterMap();
        try {
            T obj = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
            return obj;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
