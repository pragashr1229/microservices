package com.example.securityapp1;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
@RestController
@CrossOrigin
public class SecurityApp3Application2 implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApp3Application2.class, args);
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.POST) // ADMIN or USER
	//@PostMapping("/authenticate")
	public MyResponse authenticate(@RequestBody Person person) {
		System.out.println(" person "+person.getUsername());
		
		String token = Jwts.builder()
							.setClaims(new HashMap()) // public data of user
							.setSubject(person.getUsername()) //Username as subject
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
							.signWith(SignatureAlgorithm.HS256, "hello") // algorithm & key
							.compact(); 
		System.out.println(" token "+token);
		return new MyResponse(token);
	}

	@GetMapping("/") // ADMIN or USER
	public MyResponse getMessages() {
		return new MyResponse("Response from the root resource");
	}

	@GetMapping("/test") // ADMIN
	public MyResponse getMessage() {
		return new MyResponse("Response from the test resource");
	}

	@GetMapping("/test1") // ADMIN
	public MyResponse getMessage1() {
		return new MyResponse("Response from the test1 resource");
	}
	@GetMapping("/test2") // USER
	public MyResponse getMessage2() {
		return new MyResponse("Response from the test2 resource");
	}

	@GetMapping("/test3") // USER
	public MyResponse getMessage3() {
		return new MyResponse("Response from the test3 resource");
	}

	@GetMapping("/test4") // USER
	public MyResponse getMessage4() {
		return new MyResponse("Response from the test4 resource");
	}

	@Override
	public void run(String... args) throws Exception {
		Person p = new Person();
		p.setPassword(getPasswordEncoder().encode("xyz"));
		p.setUsername("xyz");
		p.setRole("ROLE_ADMIN");

		Person p1 = new Person();
		p1.setPassword(getPasswordEncoder().encode("xyz"));
		p1.setUsername("hello");
		p1.setRole("ROLE_USER");

		personService.save(p);
		personService.save(p1);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}