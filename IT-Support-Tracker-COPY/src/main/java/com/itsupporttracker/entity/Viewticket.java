package com.itsupporttracker.entity;

import java.util.List;	
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Viewticket {

	@Id
	private int ticketId;
	private int userId;
	private String categoryDesc;
	private String subCategoryDesc;
	private String subject;
	private String priority;
	private String status;
	private String assignee;
	private String link;
	@OneToMany(targetEntity = Comment.class)
	@JoinColumn(name = "ticketId")
	private List<Comment> comment;
	
	/**
	 * Viewticket table in the database to view the list of tickets
	 */

	public int getTicketId() {
		return ticketId;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}

	public String getSubject() {
		return subject;
	}

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getLink() {
		return link;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
