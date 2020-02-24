package com.hzau.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author su
 * @description
 * @date 2020/2/20
 */
public class LocalBeanUtils {
    public static void beanUserByReq(HttpServletRequest request, Object obj) throws UnsupportedEncodingException {
        Map<String, String[]> map = request.getParameterMap();
        try {
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
