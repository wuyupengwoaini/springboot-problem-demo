package com.example.cyclic.dependency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 18:29
 **/
@Service
public class HelloServiceImpl implements HelloService {

    /**
     * 当了能够获取到异步增强的代理对象，注入自身对象的引用。但是启动会出现循环应用的问题
     *
     * 最佳解决方案：加上@Lazy注解
     */
    @Autowired
    private HelloService helloService;

    @Override
    public Object hello(Integer id) {
        System.out.println("线程名称：" + Thread.currentThread().getName());
        helloService.fun1(); // 使用接口方式调用，而不是this
        return "service hello";
    }

    @Async
    @Override
    public void fun1() {
        System.out.println("线程名称：" + Thread.currentThread().getName());
    }


}
