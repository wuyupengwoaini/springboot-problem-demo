package com.example.shiro.bpp.bean;

/**
 * 模拟RedisConnectionFactory的默认实现
 */
public class MyTracingRedisConnectionFactoryImpl implements MyRedisConnectionFactory {

    private MyRedisConnectionFactory delegate;

    public MyTracingRedisConnectionFactoryImpl(MyRedisConnectionFactory delegate){
        this.delegate = delegate;
    }

    @Override
    public String getRedisConnection() {
        return "tracing----------" + delegate.getRedisConnection();
    }
}
