package com.example.mybatis.placeholder.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 16:27
 **/
@Configuration
public class MybatisConfig {
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1(DataSource dataSource1) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        sqlSessionFactoryBean.setConfiguration(ibatisConfiguration);

        sqlSessionFactoryBean.setDataSource(dataSource1);
        sqlSessionFactoryBean.setTypeAliasesPackage("sample.mybatis.domain");
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * MapperScannerConfigurer它实现了BeanDefinitionRegistryPostProcessor。
     * 这个接口在是Spring扫描Bean定义时会回调的，在BeanFactoryPostProcessor之前执行
     */
    @Bean
    MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactory sqlSessionFactory1) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory1");
        mapperScannerConfigurer.setBasePackage("sample.mybatis.mapper");
        return mapperScannerConfigurer;
    }

}
