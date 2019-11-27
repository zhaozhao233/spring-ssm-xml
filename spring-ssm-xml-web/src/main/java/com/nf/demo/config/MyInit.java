package com.nf.demo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyInit implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // 指定 spring 的配置来源
        applicationContext.register(MvcConfig.class);
        // 新建一个 DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        // 像在web.xml 里面配置一样，<servlet><servlet-name>取一个名字</servlet-name></servlet>
        ServletRegistration.Dynamic mingzi = servletContext.addServlet("mingzi", dispatcherServlet);
        // 像在web.xml 里面配置一样 <servlet-mapper><url-pattern>/</url-pattern></servlet-mapper>
        // 所有 url 都使用 DispatcherServlet 来处理
        mingzi.addMapping("/");


    }
}
