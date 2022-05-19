package com.example.springbootumc;

import com.example.springbootumc.filters.SessionManagementFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public FilterRegistrationBean<SessionManagementFilter> sessionFilter(){
        FilterRegistrationBean<SessionManagementFilter> filterBean = new FilterRegistrationBean<>();

        SessionManagementFilter filter = new SessionManagementFilter();

        filterBean.setFilter(filter);
        filterBean.addUrlPatterns("/userHome", "/adminHome","/EditDetails");
//        filterBean.setOrder(1);
        return filterBean;
    }
}
