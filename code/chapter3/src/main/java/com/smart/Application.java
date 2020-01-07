package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: yk
 * @Date: 2020/1/6 22:37
 */
@SpringBootApplication
@EnableTransactionManagement
/**
 * Spring Boot事务：
 *     1. 在主类Application中标注@EnableTransactionManagement注解开启事务支持
 *     2. 在访问Service的方法上标注@Transactional注解
 *        注意：建议不要将@Transactional注解标注在Service类级别上，该做法会导致当前Service所有方法都被事务增强
 *
 *     3. 也可以添加自定义事务管理器：
 *        @Bean
 *            public PlatformTransactionManager txManager(DataSource dataSource) {
 *                return new DataSourceTransactionManager(dataSource);
 *            }
 *
 *
 * Spring Boot配置SpringMVC框架：
 *     继承Spring Boot提供的Servlet初始化器SpringBootServletInitializer，并重写configure方法
 */
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
