package com.hzau.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static String writeValueAsString(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    public static void sendJsonToClient(ServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
