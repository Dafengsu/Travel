package com.hzau.web.servlet;

import com.hzau.domain.Category;
import com.hzau.service.CategoryService;
import com.hzau.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author su
 * @description
 * @date 2020/2/25
 */
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();
    /**
     * 查询所有
     *
     * @param request
     * @param response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> list = service.findAll();
        sendObjectAsJson(list, response);
    }
}
