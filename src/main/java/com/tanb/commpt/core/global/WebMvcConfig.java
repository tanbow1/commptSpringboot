package com.tanb.commpt.core.global;

import com.github.pagehelper.PageHelper;
import com.tanb.commpt.core.interceptor.CommHanlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Created by Tanbo on 2017/12/7.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

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

    /**
     * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
     * dialect ： oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
     * 移步至配置文件
     * @return
     */
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("dialect", "oracle");
//        properties.setProperty("autoRuntimeDialect", "true");
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }

}
