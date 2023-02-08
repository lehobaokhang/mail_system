package com.miroslav.mailsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.miroslav.mailsystem.entity.User;
import com.miroslav.mailsystem.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public void registerProcessing(@ModelAttribute("user") User user) {
		userService.register(user);
	}
}
