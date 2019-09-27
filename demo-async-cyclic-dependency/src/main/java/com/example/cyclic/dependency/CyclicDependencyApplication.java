package com.example.cyclic.dependency;

import com.example.cyclic.dependency.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description
 * @Author wuyupeng
 * @Date 2019/9/26 18:27
 **/
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.example.cyclic.dependency")
public class CyclicDependencyApplication {
    public static void main(String[] args) {
        /**
         *
         * 启动报错：BeanCurrentlyInCreationException: Error creating bean with name 'helloServiceImpl':
         * Bean with name 'helloServiceImpl' has been injected into other beans [helloServiceImpl]
         * in its raw version as part of a circular reference, but has eventually been wrapped.
         * This means that said other beans do not use the final version of the bean.
         * This is often the result of over-eager type matching - consider using 'getBeanNamesOfType' with the 'allowEagerInit' flag turned off, for example.
         *
         *  大致意思就是：创建名为“helloServiceImpl”的bean时出错：名为“helloServiceImpl”的bean已作为循环引用的
         *  一部分注入到其原始版本中的其他bean[helloServiceImpl]中，但最终已被包装。这意味着其他bean不使用bean的最终版本。
         *
         *  报错说明：Spring管理的Bean都是单例的，所以Spring默认需要保证所有使用此Bean的地方都指向的是同一个地址，
         *  也就是最终版本的Bean，否则可能就乱套了，Spring也提供了这样的自检机制
         *
         */
        ConfigurableApplicationContext context = SpringApplication.run(CyclicDependencyApplication.class, args);
        HelloService helloService = context.getBean(HelloService.class);
        helloService.hello(1);
    }
}
