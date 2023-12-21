package com.liam.spring.jpa.postgresql.repository;

import com.liam.spring.jpa.postgresql.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends CrudRepository<Task, UUID> {
	
    // Custom method to fetch all tasks ordered by newest first
    @Query("SELECT t FROM Task t ORDER BY t.createdAt DESC")
    List<Task> findAllOrderByCreatedAtDesc();
    
    // Custom method to search a task by its ID
    @Query("SELECT t FROM Task t WHERE t.id = :taskId")
    Optional<Task> findByIdCustom(@Param("taskId") UUID taskId);
    
}