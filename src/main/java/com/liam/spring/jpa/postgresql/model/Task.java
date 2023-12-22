package com.liam.spring.jpa.postgresql.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Task class represents a task with various attributes such as title,
 * description, and priority. Each task has a unique identifier (UUID) and
 * tracks information like creation time, update time, due date, resolution
 * time, and status.
 */

@Entity
@Table(name = "tasks")
public class Task {

	// PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "dueDate", nullable = false)
	private LocalDateTime dueDate;

	@Column(name = "title", nullable = false, length = 512)
	private String title;

	@Column(name = "description", nullable = false, length = 512)
	private String description;

	@Column(name = "priority", nullable = false, length = 512)
	private String priority;

	@Column(name = "status", nullable = false, length = 512)
	private String status = "open";

	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	private LocalDateTime resolvedAt;

	// CONSTRUCTORS

	/**
	 * Constructs a new Task from provided DTO
	 * 
	 * @param taskDTO
	 */
	public Task(TaskDTO taskDTO) {
		this.id = taskDTO.getId();
		this.dueDate = taskDTO.getDueDate();
		this.title = taskDTO.getTitle();
		this.description = taskDTO.getDescription();
		this.priority = taskDTO.getPriority();
		this.status = taskDTO.getStatus();
		this.createdAt = taskDTO.getCreatedAt();
		this.updatedAt = taskDTO.getUpdatedAt();
		this.resolvedAt = taskDTO.getResolvedAt();
	}

	/**
	 * An empty constructor needed for Hibernate.
	 */
	@SuppressWarnings("unused")
	private Task() {
	}

	// DTO Compatibility

	/**
	 * Converts the Task instance to a TaskDTO.
	 *
	 * @return TaskDTO representing the current state of the Task instance.
	 */
	public TaskDTO toDTO() {
		TaskDTO taskDTO = new TaskDTO(this);
		return taskDTO;
	}

	// GETTERS & SETTERS

	// ID
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	// Created At
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// Updated At
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Due Date
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	// Resolved At
	public LocalDateTime getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(LocalDateTime resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Priority
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	// Status
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}