package com.yt.boot.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-14 0:38
 */
@Configuration
public class MyDruidDataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }

//    @Bean
//    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
//        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet());
//        registrationBean.addUrlMappings("/druid/*");
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
//        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(new WebStatFilter());
//        registrationBean.setUrlPatterns(Arrays.asList("/*"));
//        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return registrationBean;
//    }
//
//    @Bean
//    public DruidStatInterceptor druidStatInterceptor() {
//        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
//        return druidStatInterceptor;
//    }

}