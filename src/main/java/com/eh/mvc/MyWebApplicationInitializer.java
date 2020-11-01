package com.eh.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 获取根容器的配置类（Spring的配置文件）父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 获取Web容器的配置类(SpringMVC配置文件)子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    // 获取DispatcherServlet的映射信息
    @Override
    protected String[] getServletMappings() {
        // 拦截所有请求(包括静态资源xxx.js，xxx.png)，不包括*.jsp
        // /*：拦截所有请求，包括*.jsp
        // jsp页面是tomcat的jsp引擎解析的，不能进行拦截
        return new String[]{"/"};
    }
}
