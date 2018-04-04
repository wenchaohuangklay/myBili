package com.klay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@MapperScan("com.klay.dao")
@EnableScheduling
public class BilibiliApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilibiliApplication.class, args);
	}
}
