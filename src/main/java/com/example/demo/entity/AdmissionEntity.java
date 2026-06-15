package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admissions")
@Data
public class AdmissionEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long studentId;
//=========================
//1. Personal Details
//=========================

public Long getStudentId() {
	return studentId;
}
public void setStudentId(Long studentId) {
	this.studentId = studentId;
}
private String fullName;
private LocalDate dateOfBirth;
private String gender;
private String category;
private String nationality;
private String aadhaarNumber;

//=========================
//2. Guardian Details
//=========================

private String fatherName;
private String fatheroccupation;
private String motherName;
private String motheroccupation;
private String guardianName;
private String annualIncome;

//=========================
//3. Other Details
//=========================

private String bloodGroup;
private String disabilityStatus;
private String disabilityDetails;
private String emergencyContact;

//=========================
//4. Contact Details
//=========================

private String mobileNumber;
private String emailId;
private String guardianMobile;

@Column(length = 1000)
private String currentAddress;

@Column(length = 1000)
private String permanentAddress;

//=========================
//5. Previous Academic Details
//=========================

private String previousSchoolCollege;
private String boardUniversity;
private String lastPassedCourse;
private String rollRegistrationNumber;
private Integer passingYear;
private String marksPercentageCgpa;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] marksheetUpload;

//=========================
//6. Admission Details
//=========================

private String ug_pg;

@ManyToOne
@JoinColumn(name = "course_id")
private CourseEntity course;
private String sessionBatch;
private String status;


//=========================
//7. Document Uploads
//=========================


@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] passportPhoto;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] signature;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] aadhaarCard;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] class10Marksheet;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] class12Marksheet;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] migrationCertificate;

@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] categoryCertificate;


@Column(unique = true)
private String username;

private String password;

@Transient
private String confirmPassword;

//=========================
//8. Final Section
//=========================

private Boolean declarationCheckbox;
private Boolean termsConditions;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public LocalDate getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getAadhaarNumber() {
	return aadhaarNumber;
}
public void setAadhaarNumber(String aadhaarNumber) {
	this.aadhaarNumber = aadhaarNumber;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getFatheroccupation() {
	return fatheroccupation;
}
public void setFatheroccupation(String fatheroccupation) {
	this.fatheroccupation = fatheroccupation;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public String getMotheroccupation() {
	return motheroccupation;
}
public void setMotheroccupation(String motheroccupation) {
	this.motheroccupation = motheroccupation;
}
public String getGuardianName() {
	return guardianName;
}
public void setGuardianName(String guardianName) {
	this.guardianName = guardianName;
}
public String getAnnualIncome() {
	return annualIncome;
}
public void setAnnualIncome(String annualIncome) {
	this.annualIncome = annualIncome;
}
public String getBloodGroup() {
	return bloodGroup;
}
public void setBloodGroup(String bloodGroup) {
	this.bloodGroup = bloodGroup;
}
public String getDisabilityStatus() {
	return disabilityStatus;
}
public void setDisabilityStatus(String disabilityStatus) {
	this.disabilityStatus = disabilityStatus;
}
public String getDisabilityDetails() {
	return disabilityDetails;
}
public void setDisabilityDetails(String disabilityDetails) {
	this.disabilityDetails = disabilityDetails;
}
public String getEmergencyContact() {
	return emergencyContact;
}
public void setEmergencyContact(String emergencyContact) {
	this.emergencyContact = emergencyContact;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getGuardianMobile() {
	return guardianMobile;
}
public void setGuardianMobile(String guardianMobile) {
	this.guardianMobile = guardianMobile;
}
public String getCurrentAddress() {
	return currentAddress;
}
public void setCurrentAddress(String currentAddress) {
	this.currentAddress = currentAddress;
}
public String getPermanentAddress() {
	return permanentAddress;
}
public void setPermanentAddress(String permanentAddress) {
	this.permanentAddress = permanentAddress;
}
public String getPreviousSchoolCollege() {
	return previousSchoolCollege;
}
public void setPreviousSchoolCollege(String previousSchoolCollege) {
	this.previousSchoolCollege = previousSchoolCollege;
}
public String getBoardUniversity() {
	return boardUniversity;
}
public void setBoardUniversity(String boardUniversity) {
	this.boardUniversity = boardUniversity;
}
public String getLastPassedCourse() {
	return lastPassedCourse;
}
public void setLastPassedCourse(String lastPassedCourse) {
	this.lastPassedCourse = lastPassedCourse;
}
public String getRollRegistrationNumber() {
	return rollRegistrationNumber;
}
public void setRollRegistrationNumber(String rollRegistrationNumber) {
	this.rollRegistrationNumber = rollRegistrationNumber;
}
public Integer getPassingYear() {
	return passingYear;
}
public void setPassingYear(Integer passingYear) {
	this.passingYear = passingYear;
}
public String getMarksPercentageCgpa() {
	return marksPercentageCgpa;
}
public void setMarksPercentageCgpa(String marksPercentageCgpa) {
	this.marksPercentageCgpa = marksPercentageCgpa;
}
public byte[] getMarksheetUpload() {
	return marksheetUpload;
}
public void setMarksheetUpload(byte[] marksheetUpload) {
	this.marksheetUpload = marksheetUpload;
}
public String getUg_pg() {
	return ug_pg;
}
public void setUg_pg(String ug_pg) {
	this.ug_pg = ug_pg;
}
public CourseEntity getCourse() {
	return course;
}
public void setCourse(CourseEntity course) {
	this.course = course;
}
public String getSessionBatch() {
	return sessionBatch;
}
public void setSessionBatch(String sessionBatch) {
	this.sessionBatch = sessionBatch;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public byte[] getPassportPhoto() {
	return passportPhoto;
}
public void setPassportPhoto(byte[] passportPhoto) {
	this.passportPhoto = passportPhoto;
}
public byte[] getSignature() {
	return signature;
}
public void setSignature(byte[] signature) {
	this.signature = signature;
}
public byte[] getAadhaarCard() {
	return aadhaarCard;
}
public void setAadhaarCard(byte[] aadhaarCard) {
	this.aadhaarCard = aadhaarCard;
}
public byte[] getClass10Marksheet() {
	return class10Marksheet;
}
public void setClass10Marksheet(byte[] class10Marksheet) {
	this.class10Marksheet = class10Marksheet;
}
public byte[] getClass12Marksheet() {
	return class12Marksheet;
}
public void setClass12Marksheet(byte[] class12Marksheet) {
	this.class12Marksheet = class12Marksheet;
}
public byte[] getMigrationCertificate() {
	return migrationCertificate;
}
public void setMigrationCertificate(byte[] migrationCertificate) {
	this.migrationCertificate = migrationCertificate;
}
public byte[] getCategoryCertificate() {
	return categoryCertificate;
}
public void setCategoryCertificate(byte[] categoryCertificate) {
	this.categoryCertificate = categoryCertificate;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public Boolean getDeclarationCheckbox() {
	return declarationCheckbox;
}
public void setDeclarationCheckbox(Boolean declarationCheckbox) {
	this.declarationCheckbox = declarationCheckbox;
}
public Boolean getTermsConditions() {
	return termsConditions;
}
public void setTermsConditions(Boolean termsConditions) {
	this.termsConditions = termsConditions;
}










}



