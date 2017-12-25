//package com.tanb.commpt.core.global;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by Tanbo on 2017/12/25.
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                //  .antMatchers( "/","/index").permitAll() //指定不需要认证的请求
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") ////指定登录页
//                .defaultSuccessUrl("/index")  //登录成功后默认跳转到
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout") //退出请求
//                .logoutSuccessUrl("/home")  //退出登录后的默认url
//                .permitAll();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/static/**");
//    }
//
//}
