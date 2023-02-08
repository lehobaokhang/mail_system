package com.miroslav.mailsystem.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetail {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "gender")
	private int gender;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;

	public UserDetail() {
		super();
	}

	public UserDetail(String id, String fullname, String avatar, int gender, Date dob, String phone,
			String address) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.avatar = avatar;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", fullname=" + fullname + ", avatar=" + avatar + ", gender=" + gender
				+ ", dob=" + dob + ", phone=" + phone + ", address=" + address + "]";
	}
}