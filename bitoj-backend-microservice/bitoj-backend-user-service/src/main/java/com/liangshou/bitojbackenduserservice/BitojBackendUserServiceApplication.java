package com.liangshou.bitojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.liangshou.bitojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.liangshou")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.liangshou.bitojbackendserviceclient.service"})
public class BitojBackendUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitojBackendUserServiceApplication.class, args);
    }
}
