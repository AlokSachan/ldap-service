package com.alok.config;

import com.alok.filters.HeadersLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class MyFilterConfig {

    @Bean
    FilterRegistrationBean<HeadersLoggingFilter> registrationBean(){
        FilterRegistrationBean<HeadersLoggingFilter> myBean = new FilterRegistrationBean<>();
        myBean.setFilter(new HeadersLoggingFilter());
        myBean.setName("fooBar");
        myBean.setOrder(-1);
        myBean.setUrlPatterns(Collections.singletonList("/test/*"));
        return myBean;
    }
}
