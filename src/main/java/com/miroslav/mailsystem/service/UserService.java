package com.miroslav.mailsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miroslav.mailsystem.entity.User;

public interface UserService extends UserDetailsService{
	public void register(User user);
}
