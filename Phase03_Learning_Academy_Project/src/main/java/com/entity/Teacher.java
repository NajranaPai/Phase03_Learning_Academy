package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Teacher_Details")
public class Teacher {

@GeneratedValue
@Column(name="Sr_No")
private int srno;

@Id
@Column(name="Teacher_Id")
private String Teacher_Id;

@Column(name="First_Name")
private String First_Name;

@Column(name="Last_Name")
private String Last_Name;

@Column(name="Qualification")
private String Qualification;

@Column(name="Address")
private String Address;

@Column(name="PhoneNo")
private String PhoneNo;

public Teacher() {
	super();
}

public int getSrno() {
	return srno;
}

public void setSrno(int srno) {
	this.srno = srno;
}

public String getTeacher_Id() {
	return Teacher_Id;
}

public void setTeacher_Id(String teacher_Id) {
	Teacher_Id = teacher_Id;
}

public String getFirst_Name() {
	return First_Name;
}

public void setFirst_Name(String first_Name) {
	First_Name = first_Name;
}

public String getLast_Name() {
	return Last_Name;
}

public void setLast_Name(String last_Name) {
	Last_Name = last_Name;
}

public String getQualification() {
	return Qualification;
}

public void setQualification(String qualification) {
	Qualification = qualification;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getPhoneNo() {
	return PhoneNo;
}

public void setPhoneNo(String phoneNo) {
	PhoneNo = phoneNo;
}
}
