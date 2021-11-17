package com.Yuzhen.ExerciseOnline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 配置扫描MyBatis接口的包路径
@MapperScan(basePackages={"com.Yuzhen.ExerciseOnline.repository"})
public class ExerciseOnlineApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExerciseOnlineApplication.class, args);
	}
}
