package com.miroslav.mailsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {
	@GetMapping("/compose")
	public String showCompose() {
		return "compose";
	}
	
	@PostMapping("/form-processing")
	public String formProcessing() {
		return "redirect:/";
	}
}
