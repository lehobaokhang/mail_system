package com.miroslav.mailsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miroslav.mailsystem.entity.UserDetail;
import com.miroslav.mailsystem.repository.UserDetailRepository;

@Service
public class UserDetailServiceImpl implements UserDetailService {
	
	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetail updateProfile(UserDetail newProfile, UserDetail oldProfile) {
		newProfile.setId(oldProfile.getId());
		newProfile.setAvatar(oldProfile.getAvatar());
		newProfile.setFullname(oldProfile.getFullname());
		return userDetailRepository.save(newProfile);
	}
	
	@Override
	public void changeAvatar(String profileId, String avatarPath) {
		UserDetail profile = userDetailRepository.getById(profileId);
		profile.setAvatar(avatarPath);
		userDetailRepository.save(profile);
	}

}
