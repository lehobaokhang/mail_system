package com.miroslav.mailsystem.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miroslav.mailsystem.config.CustomUserDetails;
import com.miroslav.mailsystem.entity.UserDetail;
import com.miroslav.mailsystem.service.UserDetailService;

@Controller
public class ProfileController {
	@Autowired
	private UserDetailService userDetailsService;

	@GetMapping("/profile")
	public String showProfile(Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		model.addAttribute("profile", userDetails.getUser().getUserDetail());
		return "profile";
	}

	@PostMapping("/update-profile")
	public String updateProfile(@ModelAttribute("profile") UserDetail profile, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		profile.setId(userDetails.getUser().getUserDetail().getId());

		UserDetail oldProfile = userDetails.getUser().getUserDetail();
		UserDetail newProfile = userDetailsService.updateProfile(profile, oldProfile);
		userDetails.getUser().setUserDetail(newProfile);

		redirectAttributes.addFlashAttribute("message", "Your profile has been changed");
		return "redirect:/profile";
	}

	@PostMapping("/change-avatar")
	public String changeAvatar(@RequestParam("avatar") MultipartFile avatar, Authentication authentication) {
		if (!avatar.isEmpty()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			String folder = "/uploads/avatars/";

			try {
				String fileName = avatar.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
				
				byte[] bytes = avatar.getBytes();
				Path path = Paths.get("src/main/resources/static" + folder + avatar.getOriginalFilename());
				Files.write(path, bytes);

				String newFileName = userDetails.getUser().getId() + "." + fileExtension;

				Path newPath = Paths.get("src/main/resources/static" + folder + newFileName);
				Files.move(path, newPath, StandardCopyOption.REPLACE_EXISTING);

				userDetails.getUser().getUserDetail().setAvatar(folder + newFileName);
			} catch (Exception e) {

			}
		}
		return "redirect:/profile";
	}
}
