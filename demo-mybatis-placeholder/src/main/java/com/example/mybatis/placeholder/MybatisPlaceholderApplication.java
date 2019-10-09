package com.example.mybatis.placeholder;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 15:22
 **/
@ComponentScan("com.example.mybatis.placeholder")
public class MybatisPlaceholderApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisPlaceholderApplication.class);
        JdbcDataSource jdbcDataSource = (JdbcDataSource)applicationContext.getBean("dataSource1");
        /**
         * 这里打印的内容是${db.user}，而不是testUser
         * 原因分析：
         * 首先，MapperScannerConfigurer它实现了BeanDefinitionRegistryPostProcessor，所以它会Spring的早期会被创建
         * 其次，从bean的依赖关系来看，mapperScannerConfigurer依赖了sqlSessionFactory1，sqlSessionFactory1依赖了dataSource1
         * 最后，yDataSourceConfig里的dataSource1被提前初始化，没有经过PropertySourcesPlaceholderConfigurer的处理，所以@Value("${db.user}") String user 里的占位符没有被处理
         *
         * 解决方案：使用environment.resolvePlaceholders("${db.user}")来解决
         */
        System.out.println(jdbcDataSource.getUser());
    }
}
