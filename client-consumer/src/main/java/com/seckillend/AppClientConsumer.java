package com.seckillend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // Nacos 注册
@EnableFeignClients // openfeign 使用
@ServletComponentScan // filter 启动
public class AppClientConsumer {
	public static void main(String[] args) {
		SpringApplication.run(AppClientConsumer.class, args);
	}
}
