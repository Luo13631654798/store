package com.lyc.config;

import com.lyc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
//@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /**
     * 配置拦截器拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("/web/regist.html");
        patterns.add("/web/product.html");
        patterns.add("/web/search.html");
        patterns.add("/user/regist");
        patterns.add("/user/login");
        patterns.add("/web/pages/client/**");
        registry.addInterceptor(interceptor).addPathPatterns().excludePathPatterns(patterns);
    }
}
