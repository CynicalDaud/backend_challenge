package com.liam.spring.jpa.postgresql.repository;

import com.liam.spring.jpa.postgresql.model.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
	
}