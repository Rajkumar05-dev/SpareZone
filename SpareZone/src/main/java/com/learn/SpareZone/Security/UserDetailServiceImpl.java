package com.learn.SpareZone.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.learn.SpareZone.Entities.User;
import com.learn.SpareZone.Repositories.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	  private UserRepository userRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("email not found")); 
		return (UserDetails) user;
	}

}
