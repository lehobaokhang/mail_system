package com.miroslav.mailsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetail userDetail;

	public User() {
		super();
	}

	public User(String id, String username, String password, String role, UserDetail userDetails) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.userDetail = userDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetails) {
		this.userDetail = userDetails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", userDetails=" + userDetail + "]";
	}
}