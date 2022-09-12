package com.itsupporttracker.entity;

import javax.persistence.Entity;	
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin_team {

	@Id
	@GeneratedValue
	private int adminId;
	private String name;
	private String emailId;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
