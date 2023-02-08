package com.miroslav.mailsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miroslav.mailsystem.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, String>{

}
