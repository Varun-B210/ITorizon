package com.itsupporttracker.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Ticket {
	
	/** Ticket table in database
	 * with the following columns
	 */
	
	@Id
	@GeneratedValue
	private int ticketId;
	private int categoryId;
	private int subCategoryId;
	private int assigneeId;
	private int reportedId;
	private String subject;
	private String description;
	private int statusId;
	private int priorityId;
	@JsonFormat(pattern="dd-MMM-yyyy hh:mm a")
	private LocalDateTime createDateTime;
	@JsonFormat(pattern="dd-MMM-yyyy hh:mm a")
	private LocalDateTime lastModifiedDateTime;
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	
	public int getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}
	
	public int getReportedId() {
		return reportedId;
	}
	public void setReportedId(int reportedId) {
		this.reportedId = reportedId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public int getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}
	
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime date) {
		this.createDateTime = date;
	}
	public LocalDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	
	
	
}
