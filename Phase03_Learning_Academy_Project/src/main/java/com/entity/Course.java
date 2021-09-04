package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Course_Details")
public class Course {

@GeneratedValue
@Column(name="Sr_No")
private int srno;

@Id
@Column(name="Course_Id")
private String Course_Id;

@Column(name="Course_Name")
private String Course_Name;
/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "Subject")
private Subject subject;*/

public Course() {
	super();
}

public int getSrno() {
	return srno;
}

public void setSrno(int srno) {
	this.srno = srno;
}

public String getCourse_Id() {
	return Course_Id;
}

public void setCourse_Id(String course_Id) {
	Course_Id = course_Id;
}

public String getCourse_Name() {
	return Course_Name;
}

public void setCourse_Name(String course_Name) {
	Course_Name = course_Name;
}


}
