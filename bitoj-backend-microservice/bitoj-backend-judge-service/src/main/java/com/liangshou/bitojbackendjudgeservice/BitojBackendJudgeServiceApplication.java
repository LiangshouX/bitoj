package com.liangshou.bitojbackendjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.liangshou")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.liangshou.bitojbackendserviceclient.service"})
public class BitojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        // 初始化消息队列，先注释掉，改用 Bean 的方式初始化消息队列（InitRabbitMqBean.java）
        // InitRabbitMq.doInit();
        final ConfigurableApplicationContext ctx = SpringApplication.run(BitojBackendJudgeServiceApplication.class, args);

//        final String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
//        try (FileWriter writer = new FileWriter("JudgeServiceBeans.txt")) {
//            for (String beanName : beanDefinitionNames) {
//                writer.write(beanName + "\n"); // 写入每个Bean的名称，每个名称占一行
//            }
//            System.out.println("String数组写入文件成功！");
//        } catch (IOException e) {
//            System.out.println("写入文件时出现错误：" + e.getMessage());
//        }
    }

}
