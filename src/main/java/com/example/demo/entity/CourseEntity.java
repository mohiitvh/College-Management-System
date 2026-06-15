package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName; //BCA - AI
    private String courseCode; //BCA

    private Double admissionFee;
    private Double semesterFee;
    private Double totalFee;

    private String duration;
    
 // Getter Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Double getAdmissionFee() {
		return admissionFee;
	}

	public void setAdmissionFee(Double admissionFee) {
		this.admissionFee = admissionFee;
	}

	public Double getSemesterFee() {
		return semesterFee;
	}

	public void setSemesterFee(Double semesterFee) {
		this.semesterFee = semesterFee;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

    
}