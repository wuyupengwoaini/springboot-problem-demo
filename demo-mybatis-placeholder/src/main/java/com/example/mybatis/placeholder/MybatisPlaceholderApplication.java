package com.example.mybatis.placeholder;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 15:22
 **/
@SpringBootApplication(scanBasePackages = "com.example.mybatis.placeholder")
public class MybatisPlaceholderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MybatisPlaceholderApplication.class, args);
        JdbcDataSource jdbcDataSource = (JdbcDataSource)applicationContext.getBean("dataSource1");
        /**
         * 这里打印的内容是${db.user}，而不是testUser
         */
        System.out.println(jdbcDataSource.getUser());
    }
}
