package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Subject_Details")
public class Subject {

@GeneratedValue
@Column(name="Sr_No")
private int srno;

@Id
@Column(name="Subject_Id")
private String Subject_Id;

@Column(name="Subject_Name")
private String Subject_Name;

@Column(name="Course_Id" , nullable=false)
private String Course_Id;

public Subject() {
	super();
}

public int getSrno() {
	return srno;
}

public void setSrno(int srno) {
	this.srno = srno;
}

public String getSubject_Id() {
	return Subject_Id;
}

public void setSubject_Id(String subject_Id) {
	Subject_Id = subject_Id;
}

public String getSubject_Name() {
	return Subject_Name;
}

public void setSubject_Name(String subject_Name) {
	Subject_Name = subject_Name;
}

public String getCourse_Id() {
	return Course_Id;
}

public void setCourse_Id(String course_Id) {
	Course_Id = course_Id;
}
}
