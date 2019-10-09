package com.example.shiro.bpp;

import com.example.shiro.bpp.bean.MyRedisConnectionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 15:22fox
 **/
@ComponentScan("com.example.shiro.bpp")
@EnableAsync
public class ShiroBppDemoApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ShiroBppDemoApplication.class);
        MyRedisConnectionFactory bean = configApplicationContext.getBean(MyRedisConnectionFactory.class);
        /**
         * 1.正常情况下，应该打印tracing----------MyRedisConnection
         * 但是实际情况下，打印的却是MyRedisConnection。说明MyRedisConnectionFactory并没有切换为MyTracingRedisConnectionFactoryImpl
         *
         * 2.注意观察启动日志：Bean 'myRedisConnectionFactoryImpl' of type [com.example.demo.bean.MyRedisConnectionFactoryImpl] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
         *   大致意思：myRedisConnectionFactoryImpl并没有被所有的BeanPostProcessors处理，比如auto-proxying处理。
         *   实际上这里myRedisConnectionFactoryImpl是没有被
         *
         * 3.你可以试着把ShiroMockBeanPostProcessor中对MyRedisConnectionFactory的依赖删除，这样的话就是预想中的情况
         *
         * 4.解决方案
         *    修改BeanPostProcessor的order顺序
         *    不共享对象。即两个BeanPostProcessor依赖不同的对象。
         *    懒加载。+@Lazy注解
         *    用构造方案 + ObjectProvider
         */
        System.out.println(bean.getRedisConnection());
    }
}
