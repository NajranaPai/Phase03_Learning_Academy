package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Teacher_Subject")
public class TeacherSubject {

@Id
@Column(name="Sr_No")
private int srno;

@Column(name="Teacher_Id")
private String Teacher_Id;

@Column(name="Course_Id")
private String Course_Id;

@Column(name="Subject_Id")
private String Subject_Id;

public TeacherSubject() {
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

public String getCourse_Id() {
	return Course_Id;
}

public void setCourse_Id(String course_Id) {
	Course_Id = course_Id;
}

public String getSubject_Id() {
	return Subject_Id;
}

public void setSubject_Id(String subject_Id) {
	Subject_Id = subject_Id;
}

}
