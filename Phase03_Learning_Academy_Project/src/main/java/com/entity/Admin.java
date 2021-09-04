package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

@Id
@GeneratedValue()
@Column(name="Id")
private int adminid;

@Column(name="First_Name")
private String fname;

@Column(name="Last_Name")
private String lname;

@Column(name="Email")
private String email;

@Column(name="Password")
private String password;

@Column(name="mobile")
private String mobile;

public Admin() {
	super();
}

public Admin(String fname, String lname, String email, String password,String mobile) {
	super();
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.password = password;
	this.mobile=mobile;
}

 

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public int getAdminid() {
	return adminid;
}

public void setAdminid(int adminid) {
	this.adminid = adminid;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
