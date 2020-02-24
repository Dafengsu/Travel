package com.hzau.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author su
 * @description
 * @date 2020/2/20
 */
public class LocalBeanUtils {


    /**
     *  把请求数据封装成bean
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T beanUserByReq(HttpServletRequest request, Class<T> clazz) {

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

    /**
     * sql查询一个对象，并返回封装对象
     * @param template
     * @param sql
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T findOneObject(JdbcTemplate template, String sql, Class<T> clazz, Object... args) {
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Json转换工具类
     */
    private final static ObjectMapper mapper = new ObjectMapper();
    /**
     * 把对象以JSON的形式发送到response
     * @param value
     * @param response
     * @throws IOException
     */
    public static void sendObjectAsJson(Object value, ServletResponse response) throws IOException {
        sendJsonToClient(response, writeValueAsString(value));
    }

    /**
     * 对象转JSON
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public static String writeValueAsString(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    /**
     * 发送JSON数据到response
     * @param response
     * @param json
     * @throws IOException
     */
    public static void sendJsonToClient(ServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
