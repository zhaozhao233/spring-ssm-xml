package com.nf.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInterceptor;
import com.nf.demo.controller.MyFirstInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan({"com.nf.demo.controller","com.nf.demo.service.impl"})//扫描添加到spring
@EnableWebMvc //等价于xml 的 mvc:annotation-driven 启用注解配置的方式
@PropertySource("classpath:db.properties") // 导入外部文件
@MapperScan({"com.nf.demo.dao"}) // 扫描 mybatis 要用的 dao 接口
@EnableTransactionManagement // 启用事务 <tx:annotation-driven>
public class MvcConfig implements WebMvcConfigurer {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;



    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setFilters("stat");
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(600000);
        dataSource.setMinIdle(1);
        dataSource.setTimeBetweenConnectErrorMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws SQLException, IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        // 添加 mapper 文件
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources("classpath*:*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        // 添加配置，日志
        sqlSessionFactoryBean.setConfiguration(configuration());
        // 添加插件，分页
        sqlSessionFactoryBean.setPlugins(pageInterceptor());
        return sqlSessionFactoryBean;
    }

    // 日志
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 不知道是什么，反正就要配
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    //额外的插件，比如分页
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("supportMethodsArguments","true");
        properties.put("reasonable","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    // 视图解析器
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        System.out.println("视图解析器启动...");
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        return internalResourceViewResolver;
    }



    // 事务
    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(druidDataSource());
        return transactionManager;
    }

    // 拦截器（WebMvcConfigurer接口的类）
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyFirstInterceptor firstInterceptor = new MyFirstInterceptor();
        InterceptorRegistration registration = registry.addInterceptor(firstInterceptor);
        registration.addPathPatterns("/**");
    }

    // 格式化器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    // 静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration registration
                = registry.addResourceHandler("/static/**");
        registration.addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // .jsp()方法 有默认的值，也可以自己设置
        registry.jsp();
    }

    // 这里添加的转化器会导致不会添加默认转换器
    // 如果想在保留默认转换器的情况下添加消息转换器，可以重写 extendMessageConverters 方法
    // 今天漏讲了消息转换器的设置。请记住消息转换器与格式化器是两码事的。
    //格式化器是用在数据绑定时，消息转换器是用在生成响应体（ResponseBody）以及读取请求体（RequestBody）的时候的数据转换
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(sdf);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converters.add(converter);
    }
}
