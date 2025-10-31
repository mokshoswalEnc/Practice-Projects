package com.fs.entity;

import jakarta.persistence.*;

@Entity
public class Student {
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", course=" + course + "]";
	}
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long id, String name, String email, String course) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.course = course;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String course;
	
}
