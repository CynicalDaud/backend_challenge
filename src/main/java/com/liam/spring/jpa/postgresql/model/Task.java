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
 * The Task class represents a task with various attributes such as title, description, and priority.
 * Each task has a unique identifier (UUID) and tracks information like creation time, update time,
 * due date, resolution time, and status.
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
    
    @Column(name="title", nullable=false, length=512)
    private String title;
    
    @Column(name="description", nullable=false, length=512)
    private String description;
    
    @Column(name="priority", nullable=false, length=512)
    private String priority;
    
    @Column(name="status", nullable=false, length=512)
    private String status = "open";
    
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;
    
    // CONSTRUCTORS   
    /**
     * Constructs a new Task with the provided attributes and default values.
     *
     * @param dueDate     The date and time by which the task is expected to be completed.
     * @param title       The title or name of the task.
     * @param description A detailed description of the task.
     * @param priority    The priority level of the task (e.g., high, medium, low).
     * 
     * Note: 'createdAt' and 'updatedAt' are NOW by default, 'resolvedAt' is null and 'status' open.
     */
    public Task(LocalDateTime dueDate, String title, String description, String priority) {
        this(LocalDateTime.now(), dueDate, null, title, description, priority, "open");
    }

    /**
     * Constructs a new Task with the provided attributes.
     *
     * @param createdAt   The date and time when the task was created.
     * @param dueDate     The date and time by which the task is expected to be completed.
     * @param resolvedAt  The date and time when the task was resolved or completed.
     * @param title       The title or name of the task.
     * @param description A detailed description of the task.
     * @param priority    The priority level of the task (e.g., high, medium, low).
     * @param status      The current status of the task (e.g., open, in progress, completed).
     *
     * Note: The 'updatedAt' parameter will be initialised equal to 'createdAt'
     */
    public Task(LocalDateTime createdAt, LocalDateTime dueDate, LocalDateTime resolvedAt, 
    			String title, String description, String priority, String status) 
    {
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.dueDate = dueDate;
        this.resolvedAt = resolvedAt;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }
    
    /**
     * An empty constructor needed for Hibernate.
     */
    private Task() {}

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