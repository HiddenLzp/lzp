package com.lzp.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc配置
 *
 * @authorHmLzp
 * @create 2019 - 06 - 06 22:36
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin");
        super.addInterceptors(registry);
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //浏览器发送请求来到login页面
                registry.addViewController("/admin").setViewName("login");
            }
        };
        return adapter;
    }
}
