package com.liam.spring.jpa.postgresql.controller;

import com.liam.spring.jpa.postgresql.model.Task;
import com.liam.spring.jpa.postgresql.services.TaskServices;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  private final TaskServices taskService;

  @Autowired
  public TaskController(TaskServices taskService) {
	  this.taskService = taskService;
  }
  
  @GetMapping("/tasks")
  public Iterable<Task> fetchAllTasks() {
	  return this.taskService.getAllTasksDesc();
  }
  
  @GetMapping("tasks/id/{taskId}")
  public ResponseEntity<Task> getTaskById(@PathVariable (name="taskId") UUID taskId) 
  {
	  Optional<Task> task = taskService.getTaskById(taskId);
      return task.map(ResponseEntity::ok).orElseGet(() ->
      				  ResponseEntity.notFound().build());
  }

  @PostMapping("/tasks/add")
  public Task addTask(@RequestBody Task task) {
	  return this.taskService.saveTask(task);
  }
  
  @DeleteMapping("/tasks/delete/{taskId}")
  public ResponseEntity<Task> deleteTaskById(@PathVariable (name="taskId") UUID taskId) 
  {	
	  Optional<Task> task = taskService.deleteTaskById(taskId);
      return task.map(ResponseEntity::ok).orElseGet(() ->
      				  ResponseEntity.notFound().build());
  }  
  
  @PutMapping("/tasks/replace/{taskId}")
  public ResponseEntity<Task> changeTask(@PathVariable (name="taskId") UUID taskId,
		  				 @RequestBody Task taskNew)
{	
	  taskNew.setId(taskId);
	  System.out.println(taskNew.getId());
	  System.out.println("");
	  Optional<Task> task = this.taskService.changeTask(taskId, taskNew);
	  return task.map(ResponseEntity::ok).orElseGet(() ->
	  				  ResponseEntity.notFound().build());
  }
}
