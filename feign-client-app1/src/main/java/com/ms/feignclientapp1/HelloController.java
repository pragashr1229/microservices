package com.ms.feignclientapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private ProxyAppForClient appForClient;
	
	@GetMapping("/hello")
	public String getHello() {
		return "From Hello :"+ appForClient.callTest1();
	}

}
