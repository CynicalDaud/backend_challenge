package com.liam.spring.jpa.postgresql.controller;

import com.liam.spring.jpa.postgresql.model.Task;
import com.liam.spring.jpa.postgresql.repository.TaskRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  private final TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }
  
  @GetMapping("/tasks")
  public Iterable<Task> fetchAllTasks() {
    return this.taskRepository.findAll();
  }

  @PostMapping("/tasks")
  public Task addTask(@RequestBody Task task) {
    return this.taskRepository.save(task);
  }
}
