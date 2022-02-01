package com.ms.feignclientapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="server-app",url="http://localhost:9091")
public interface ProxyForServerApp {

	@GetMapping("/test1")
	public String callTest1();
}
