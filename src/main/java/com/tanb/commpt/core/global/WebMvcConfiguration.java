package com.tanb.commpt.core.global;

import com.tanb.commpt.core.interceptor.CommHanlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Tanbo on 2017/12/7.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    SystemConfiguration systemConfiguration;

    /**
     * 需提前注册bean 否则CommHanlerInterceptor内无法正确注入
     *
     * @return
     */
    @Bean
    CommHanlerInterceptor commHanlerInterceptor() {
        return new CommHanlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(commHanlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/test/**", "/static/**", "/templates/**", "/login/**");
        super.addInterceptors(registry);
    }


}
