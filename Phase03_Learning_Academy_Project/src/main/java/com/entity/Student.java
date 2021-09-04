package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student_Details")
public class Student {

@GeneratedValue
@Column(name="Sr_No")
private int srno;

@Id
@Column(name="Student_Id")
private String Student_Id;

@Column(name="First_Name")
private String First_Name;

@Column(name="Last_Name")
private String Last_Name;	

@Column(name="Education")
private String Education;

@Column(name="Address")
private String Address;

@Column(name="PhoneNo")
private String PhoneNo;

public Student() {
	super();
}

public int getSrno() {
	return srno;
}

public void setSrno(int srno) {
	this.srno = srno;
}

public String getStudent_Id() {
	return Student_Id;
}

public void setStudent_Id(String student_Id) {
	Student_Id = student_Id;
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

public String getEducation() {
	return Education;
}

public void setEducation(String education) {
	Education = education;
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
