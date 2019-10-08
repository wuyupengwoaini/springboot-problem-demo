package com.example.shiro.bpp.bpp;

import com.example.shiro.bpp.bean.MyRedisConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 模拟shiro的BeanPostProcessor，并且该BeanPostProcessor依赖模拟的RedisConnectionFactory
 */
@Component
public class ShiroMockBeanPostProcessor implements BeanPostProcessor {

    /**
     * 这是关键！！！！！
     * 这里ShiroMockBeanPostProcessor依赖了MyRedisConnectionFactory。
     * 那么Spring容器就会在实例化BeanPostProcessor的时候实例化MyRedisConnectionFactory，
     * 导致MyRedisConnectionFactory提前实例化。
     *
     *  解决方案：
     * 1.可以通过使用@Lazy注解来防止Bean提前加载
     * 2.使用构造方案 + ObjectProvider
     */
    @Autowired
    private MyRedisConnectionFactory myRedisConnectionFactory;

//    private ObjectProvider<MyRedisConnectionFactory> provider;
//    public ShiroMockBeanPostProcessor(ObjectProvider<MyRedisConnectionFactory> provider){
//        this.provider = provider;
//    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // System.out.println("myRedisConnectionFactory"+myRedisConnectionFactory);
        // System.out.println("myRedisConnectionFactory"+provider.getIfAvailable());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}