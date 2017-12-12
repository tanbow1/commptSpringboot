package com.tanb.commpt.core.global;

import org.jboss.logging.Logger;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Tanbo on 2017/8/29.
 * <p>
 * web.xml
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = Logger.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.info("应用初始化，启动中...");

        try {
            SystemContext.singleton().init();
            LOGGER.info("配置项加载完成.");
        } catch (Exception e) {
            LOGGER.error("配置项加载失败:" + e);
        }

    }
}
