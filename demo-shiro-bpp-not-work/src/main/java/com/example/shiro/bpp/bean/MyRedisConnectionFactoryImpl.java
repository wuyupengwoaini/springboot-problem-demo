package com.example.shiro.bpp.bean;

import org.springframework.stereotype.Component;

@Component
public class MyRedisConnectionFactoryImpl implements MyRedisConnectionFactory {
    @Override
    public String getRedisConnection() {
        return "MyRedisConnection";
    }
}
