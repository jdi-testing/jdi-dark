package com.epam.jdi.bookstore.utils.filter;


import com.epam.jdi.tools.DataClass;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.jdi.bookstore.BookstoreApiApplication.logger;

public class CustomCorsFilter extends DataClass<CustomCorsFilter> implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        logger.debug("handle request : {} {}", request.getMethod(), request.getRequestURL());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type, authorization");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("init cors filter");
    }

    @Override
    public void destroy() {
        logger.debug("destroy cors filter");
    }

}
