package com.liam.spring.jpa.postgresql.repository;

import com.liam.spring.jpa.postgresql.model.Task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing and managing Task entities in the database.
 */
public interface TaskRepository extends CrudRepository<Task, UUID> {
	
    /**
     * Custom query to fetch all tasks ordered by creation date in descending order.
     * The query is custom so as to allow for sorting
     *
     * @return List of tasks ordered by creation date (newest first).
     */    
	@Query("SELECT t FROM Task t ORDER BY t.createdAt DESC")
    List<Task> findAllOrderByCreatedAtDesc();
    
}