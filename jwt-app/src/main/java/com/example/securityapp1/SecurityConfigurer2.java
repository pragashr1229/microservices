package com.example.securityapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer2 extends WebSecurityConfigurerAdapter {

	@Autowired
	private PersonService personService;
	
	
	  @Autowired private JwtFilter filter1;
	 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(personService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // for post,put,patch,delete
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // to access application by
																						// angular client
				.antMatchers("/authenticate").hasAnyRole("ADMIN", "USER")
				.antMatchers("/test").hasAnyRole("ADMIN", "USER")
				.antMatchers("/test").hasRole("ADMIN")
				.antMatchers("/test1").hasRole("ADMIN")
				.antMatchers("/test2").hasRole("ADMIN")
				.antMatchers("/test3").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				.and()
				.formLogin();

		http.addFilterBefore(filter1, UsernamePasswordAuthenticationFilter.class);
	}

}
