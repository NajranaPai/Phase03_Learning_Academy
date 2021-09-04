package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student_Course")
public class StudentCourse {

@Id
@Column(name="Sr_No")
private int srno;

@Column(name="Student_Id")
private String Student_Id;

@Column(name="Course_Id")
private String Course_Id;

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

public String getCourse_Id() {
	return Course_Id;
}

public void setCourse_Id(String course_Id) {
	Course_Id = course_Id;
}

}
