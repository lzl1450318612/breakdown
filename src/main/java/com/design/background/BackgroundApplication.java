package com.design.background;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.design.background.mapper")
@EnableCaching
@EnableScheduling
public class BackgroundApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackgroundApplication.class, args);
	}
}