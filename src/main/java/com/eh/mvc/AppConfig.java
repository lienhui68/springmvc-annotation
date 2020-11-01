package com.eh.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

// springMvc子容器只扫描Controller
// useDefaultFilters = false禁用默认的过滤规则
@ComponentScan(value = "com.eh.mvc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
}, useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    // 自定义视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 默认registry.jsp();，return jsp("/WEB-INF/", ".jsp");
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    /**
     * 相当于在配置文件中写 <mvc:default-servlet-handler/>
     * 将SpringMVC处理不了的请求交给tomcat，静态资源就可以访问
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 开启
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.registerCallableInterceptors()
    }
}
