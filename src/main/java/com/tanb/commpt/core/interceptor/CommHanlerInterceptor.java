package com.tanb.commpt.core.interceptor;

import com.tanb.commpt.core.global.SystemConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 通用拦截器
 *
 * @author Tanbo
 */
public class CommHanlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommHanlerInterceptor.class);

    @Autowired
    private SystemConfigure systemConfigure;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info(">>>CommHanlerInterceptor>>>>>>>");

        boolean flag = false;
        String url = request.getRequestURL().toString();

        LOGGER.info(" interceptor 当前url : " + url);

        String sessionId = request.getParameter("sessionId");

        String[] IGNORE_URI = systemConfigure.INTERCEPTOR_IGNORE_URI.split(",");
        LOGGER.info("忽略拦截：" + Arrays.toString(IGNORE_URI));

        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                LOGGER.info("忽略该URL:[" + url + "]");
                break;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        LOGGER.debug(">>>CommHanlerInterceptor>>>>>>>Controller方法调用之后");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        LOGGER.debug(">>>CommHanlerInterceptor>>>>>>>请求结束之后被调用，也是在DispatcherServlet 渲染了对应的视图之后执行（主要用于进行资源清理工作）");
    }
}
