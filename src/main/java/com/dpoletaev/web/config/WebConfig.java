package com.dpoletaev.web.config;

import com.dpoletaev.web.filter.CustomRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Bean
    public CustomRequestLoggingFilter commonsRequestLoggingFilter() {
        return new CustomRequestLoggingFilter();
    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggingInterceptor());
//    }
}
