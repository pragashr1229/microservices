package com.ms.feignclientapp1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="server-app1",url="http://localhost:7071")

//http://localhost:7071/test
public interface ProxyAppForClient {
	
	@GetMapping("/test")
	public String callTest1();

}
