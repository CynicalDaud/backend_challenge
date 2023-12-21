package com.liam.spring.jpa.postgresql.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.spring.jpa.postgresql.model.Task;
import com.liam.spring.jpa.postgresql.repository.TaskRepository;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksDesc() {
    	System.out.println("\n Getting all tasks \n");
        return taskRepository.findAllOrderByCreatedAtDesc();
    }

    public Optional<Task> getTaskById(UUID taskId) {
    	System.out.println("\n Getting task with ID: " + taskId + "\n");
        return taskRepository.findByIdCustom(taskId);
    }
    
    public Task saveTask(Task task) {
    	System.out.println("\n Saving task with ID: " + task.getId() + "\n");
    	return taskRepository.save(task);
    }
    
    public Optional<Task> deleteTaskById(UUID taskId) {
        System.out.println("\n Deleting task with ID: " + taskId);
        // Try to fetch the task to be deleted
        Optional<Task> task = taskRepository.findByIdCustom(taskId);
        // If the task exists, delete it
        task.ifPresent(t -> {
            taskRepository.deleteById(taskId);
            System.out.println("Task deleted \n");
        });

        return task;
    }
    
    public Optional<Task> changeTask(UUID taskId, Task taskNew) {
    	System.out.println("\n Replacing task with ID: "+taskId);
    	// First attempt to remove the taskId element
    	Optional<Task> taskOld = getTaskById(taskId);
    	return taskOld.map(task -> {
            taskNew.setId(taskId); // Set the ID from taskOld to taskNew
            taskNew.setCreatedAt(task.getCreatedAt()); // Set the creation date 
            saveTask(taskNew); // Save the updated task
            return Optional.of(taskNew);
        }).orElse(taskOld);
    }
	
}