package com.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import com.dao.UserDAO;
 
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserDAO userdao;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		com.model.User user = userdao.findByUsername(username).get();
		Hibernate.initialize(user.getRoles());
		
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role->new SimpleGrantedAuthority(role.getRole_name()))
				.collect(Collectors.toList());
		org.springframework.security.core.userdetails.User dbuser = new
				org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
		return dbuser;
	}
 
}
 