package com.ms.feignclientapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.ms.feignclientapp1")
public class FeignClientApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApp1Application.class, args);
	}

}
