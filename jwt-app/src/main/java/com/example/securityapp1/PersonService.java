package com.example.securityapp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService{
	
	@Autowired
	private PersonRepository personRepository;

	
	// for all the service UserDetails is not required,
	// only for the service where we are implementing spring security we need to implement UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" userName received from LoginForm "+username);
		Person person = personRepository.findById(username).get();
		System.out.println(" Person details fetched from db based on username "+username);

		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(person.getRole()));
		
		// roles should be pass as arrayList
		UserDetails user = new User(username,person.getPassword(),roles);
				
		return user;
	}
	
	public Person save(Person person) {
		personRepository.save(person);
		return person;
	}
	
}
