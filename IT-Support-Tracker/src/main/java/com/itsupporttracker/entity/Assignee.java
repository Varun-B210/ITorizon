package com.itsupporttracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Assignee {
	
	@Id
	@GeneratedValue
	private int assigneeId;
	private String Assignee;
	
	
	public int getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}
	public String getAssignee() {
		return Assignee;
	}
	public void setAssignee(String assignee) {
		Assignee = assignee;
	}
	
	

}
