package com.nf.demo.config;

import com.nf.demo.controller.FirstServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class MyServletContationInit implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // 指定 spring 的配置来源
        applicationContext.register(MvcConfig.class);
        ServletRegistration.Dynamic dynamic = ctx.addServlet("houzi",new DispatcherServlet());
        dynamic.addMapping("/second");
        ServletRegistration.Dynamic first = ctx.addServlet("first", new FirstServlet());
        first.addMapping("/first");
    }
}
