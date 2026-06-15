package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="admins")
public class Admin {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
private String email;
private String adminPass;
private String phone;
private String password;
@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] adminPhoto;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAdminPass() {
	return adminPass;
}
public void setAdminPass(String adminPass) {
	this.adminPass = adminPass;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public byte[] getAdminPhoto() {
	return adminPhoto;
}
public void setAdminPhoto(byte[] adminPhoto) {
	this.adminPhoto = adminPhoto;
}



}