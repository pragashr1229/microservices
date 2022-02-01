package com.example.securityapp1;


import java.util.function.Function;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private PersonService personService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		String str = request.getHeader("Authorization");
		//authorization info received from Header Basic eHl6Onh5eg==

		System.out.println("authorization info received from Header " + str);
		String userName = null;
		
	
		
		// get userName from the request
		if (str != null && str.startsWith("jwt ")) {
			System.out.println(" Received request startsWith jwt");
			str = str.substring(4);
			Claims claims = Jwts.parser().setSigningKey("hello").parseClaimsJws(str).getBody();
			Function<Claims, Object> f1 = Claims::getSubject;
			userName = (String) f1.apply(claims);
		}
		if (userName != null) {
			UserDetails ud = personService.loadUserByUsername(userName);
			//pass the userdetails to spring api to authorize the user
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ud, null,
					ud.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		filterChain.doFilter(request, response);
	}
}