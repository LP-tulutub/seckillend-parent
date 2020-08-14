package com.seckillend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.seckillend.mapper") //@MapperScan 用户扫描MyBatis的Mapper接口
public class GoodsServiceProducer {

	public static void main(String[] args) {
		SpringApplication.run(GoodsServiceProducer.class, args);
	}
}
