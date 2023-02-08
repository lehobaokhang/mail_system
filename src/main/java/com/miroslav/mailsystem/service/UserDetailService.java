package com.miroslav.mailsystem.service;

import com.miroslav.mailsystem.entity.UserDetail;

public interface UserDetailService {
	public UserDetail updateProfile(UserDetail newProfile, UserDetail oldProfile);
	
	public void changeAvatar(String profileId, String avatarPath);
}
