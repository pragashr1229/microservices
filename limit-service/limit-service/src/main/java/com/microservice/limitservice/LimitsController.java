package com.microservice.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

	@Autowired
	private LimitsConfiguration configuration;

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(10, 100);
	}

	@GetMapping("/limitswithconfig")
	public Limits retrieveLimitsWithConfig() {
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	}

}
