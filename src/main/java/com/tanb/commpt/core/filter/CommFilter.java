package com.tanb.commpt.core.filter;


import com.tanb.commpt.core.global.SystemContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tanbo on 2017/8/26.
 */
public class CommFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CommFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("========>CommFilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String url = httpServletRequest.getRequestURL().toString();

        System.out.println(" filter 当前url : " + url);

        String[] IGNORE_URI = SystemContext.singleton().getValueAsString("filter.ignoreUri").split(",");
        logger.info("忽略过滤：" + Arrays.toString(IGNORE_URI));

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
