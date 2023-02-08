package com.miroslav.mailsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miroslav.mailsystem.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	@Query(value = "SELECT u FROM User u WHERE u.username= :username")
	User findByUsername(@Param("username") String username);
}
