package com.miroslav.mailsystem.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miroslav.mailsystem.config.CustomUserDetails;
import com.miroslav.mailsystem.entity.User;
import com.miroslav.mailsystem.entity.UserDetail;
import com.miroslav.mailsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomUserDetails(user);
	}

	@Override
	public void register(User user) {
		user.setRole("ROLE_EMPLOYEE");
		
		String encodePassword = passwordEncoder.encode(user.getPassword());
		String id = UUID.randomUUID().toString();
		
		UserDetail userDetail = new UserDetail();
		userDetail.setId(UUID.randomUUID().toString());
		
		user.setId(id);		
		user.setPassword(encodePassword);
		user.setUserDetail(userDetail);
		
		userRepository.save(user);
	}

}
