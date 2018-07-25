package com.etn.shoppingmall.core.config;


import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 分页信息过滤器
 */
@WebFilter(urlPatterns = "/*")
public class SystemContextFilter implements Filter {
    private Integer pageSize;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        try {
            pageSize = Integer.parseInt(cfg.getInitParameter("limit"));
        } catch (NumberFormatException e) {
            pageSize = 10;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Integer pageIndex = 0;
        try {
            pageIndex = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
        }
        try {
            SystemContext.setOrder(req.getParameter("order"));
            SystemContext.setSort(req.getParameter("sort"));
            SystemContext.setPageOffset(Pager.offset(pageIndex, pageSize));
            SystemContext.setPageSize(pageSize);
            SystemContext.setRealPath(((HttpServletRequest) req).getSession().getServletContext().getRealPath("/"));
            chain.doFilter(req, resp);
        } finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
            SystemContext.removeRealPath();
        }
    }

    @Override
    public void destroy() {
    }


}
