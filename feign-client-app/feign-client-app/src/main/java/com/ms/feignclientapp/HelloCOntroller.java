package com.ms.feignclientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCOntroller {
	
	@Autowired
	private ProxyForServerApp proxyForServerApp;
	
	@GetMapping("/hello1")
	public String hello1() {
		return "from hello1";
	}
	
	@GetMapping("/hello")
	public String hello() {
		String results = proxyForServerApp.callTest1();
		System.out.println("reuslt ==>"+results);
		return results;
	}
}
