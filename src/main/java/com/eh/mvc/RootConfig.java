package com.eh.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

// 根容器扫描com.eh.mvc包下所有组件，但不扫描Controller注解的类
@ComponentScan(value = "com.eh.mvc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class RootConfig {
}
