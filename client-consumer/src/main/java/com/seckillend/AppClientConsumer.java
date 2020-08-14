package com.seckillend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@ServletComponentScan // filter 启动
public class AppClientConsumer {

	public static void main(String[] args) {
		SpringApplication.run(AppClientConsumer.class, args);
	}
}
