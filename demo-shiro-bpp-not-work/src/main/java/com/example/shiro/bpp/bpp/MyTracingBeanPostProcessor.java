package com.example.shiro.bpp.bpp;

import com.example.shiro.bpp.bean.MyRedisConnectionFactory;
import com.example.shiro.bpp.bean.MyTracingRedisConnectionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 把模拟的RedisConnectionFactory[MyTracingRedisConnectionFactoryImpl]替换为
 * 带有模拟Tracing功能的RedisConnectionFactory(MyTracingRedisConnectionFactoryImpl)
 */
@Component
public class MyTracingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyRedisConnectionFactory && !(bean instanceof MyTracingRedisConnectionFactoryImpl)){
            // 这里是不会执行的. 因为MyRedisConnectionFactory的实现类MyRedisConnectionFactoryImpl
            // 已经在实例化ShiroMockBeanPostProcessor的时候注入到ShiroMockBeanPostProcessor中了
            // 所以MyRedisConnectionFactoryImpl就存在于容器的缓存中了，这样BeanPostProcessor就无法干预MyRedisConnectionFactoryImpl的实例化过程了
            System.out.println("替换MyRedisConnectionFactory为MyTracingRedisConnectionFactoryImpl");
            return new MyTracingRedisConnectionFactoryImpl((MyRedisConnectionFactory)bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
