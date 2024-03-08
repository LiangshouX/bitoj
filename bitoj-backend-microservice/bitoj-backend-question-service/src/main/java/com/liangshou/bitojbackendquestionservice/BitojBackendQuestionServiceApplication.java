package com.liangshou.bitojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.liangshou.bitojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.liangshou")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.liangshou.bitojbackendserviceclient.service"})
public class BitojBackendQuestionServiceApplication {

    public static void main(String[] args) {

        final ConfigurableApplicationContext ctx = SpringApplication.run(BitojBackendQuestionServiceApplication.class, args);


//        final String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

}
