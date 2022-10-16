package com.OnlinehomedecorStore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserModel")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;
	private String userName;
	private String userPassword;
	private Long mobileNumber;
	@Column(unique = true)
	private String emailId;
	private String userRole;

	public UserModel() {
	}

	public UserModel(int userID, String userName, String userPassword, Long mobileNumber, String email_id,
			String userRole) {
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.mobileNumber = mobileNumber;
		this.emailId = email_id;
		this.userRole = userRole;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
