package com.liam.spring.jpa.postgresql.model;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * The TaskDTO class represents a Data Transfer Object (DTO) for the Task entity.
 * It is used to transfer data between the service layer and the presentation layer.
 */
public class TaskDTO {
	
    private UUID id;
    private LocalDateTime dueDate;
    private String title;
    private String description;
    private String priority;
    private String status = "open";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;
    
    /**
     * Constructs a new TaskDTO based on the given Task entity.
     * @param entity The Task entity from which to create the TaskDTO.
     */    
    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.dueDate 	 = entity.getDueDate();
        this.title 		 = entity.getTitle();
        this.description = entity.getDescription();
        this.priority 	 = entity.getPriority();
        this.status 	 = entity.getStatus();
        this.createdAt 	 = entity.getCreatedAt();
		this.updatedAt 	 = entity.getUpdatedAt();
        this.resolvedAt  = entity.getResolvedAt();
    }
    
    /**
     * Constructs a new TaskDTO with minimal attributes.
     * For use via POST end-point.
     *
     * @param dueDate     The date and time by which the task is expected to be completed.
     * @param title       The title or name of the task.
     * @param description A detailed description of the task.
     * @param priority    The priority level of the task (e.g. High, Medium, Low).
     * 
     * Note: 'createdAt' and 'updatedAt' are NOW by default, 'resolvedAt' is null and 'status' open.
     */
    public TaskDTO(LocalDateTime dueDate, String title, String description, String priority) {
        this(dueDate, title, description, priority, "open", LocalDateTime.now(), null);
    }

    /**
     * Constructs a new TaskDTO with all attributes (generating new ID).
     * Used by scheduler to create new random task.
     *
     * @param createdAt   The date and time when the task was created.
     * @param dueDate     The date and time by which the task is expected to be completed.
     * @param resolvedAt  The date and time when the task was resolved or completed.
     * @param title       The title or name of the task.
     * @param description A detailed description of the task.
     * @param priority    The priority level of the task (e.g. High, Medium, Low).
     * @param status      The current status of the task (e.g. Open, In Progress, Closed).
     *
     * Note: The 'updatedAt' parameter will be initialised equal to 'createdAt'
     */
    public TaskDTO(LocalDateTime dueDate, String title, String description, String priority, 
    			String status, LocalDateTime createdAt, LocalDateTime resolvedAt) 
    {
        this.dueDate 	 = dueDate;
        this.title 		 = title ;
        this.description = description;
        this.priority 	 = priority;
        this.status 	 = status;
        this.createdAt 	 = createdAt;
		this.updatedAt 	 = createdAt;
        this.resolvedAt  = resolvedAt;
    }
    
    /**
     * An empty constructor needed for Hibernate.
     */
    private TaskDTO() {}
    
    // Entity Compatibility

    /**
     * Converts the Task instance to a TaskDTO.
     *
     * @return TaskDTO representing the current state of the Task instance.
     */
    public Task toEntity() {
        Task task = new Task(this);
        return task;
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