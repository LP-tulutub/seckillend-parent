package com.seckillend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.seckillend.mapper") //@MapperScan 用户扫描MyBatis的Mapper接口
public class GoodsServiceProducer {
	/**
	 * 在 run 之前加入下面语句就可以最先导入 system 的 property，
	 * System.setProperty("druid.wall.updateAllow", "false");
	 * 也可以使用自定义自动配置
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GoodsServiceProducer.class, args);
	}
}
