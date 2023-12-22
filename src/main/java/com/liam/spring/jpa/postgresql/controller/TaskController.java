package com.liam.spring.jpa.postgresql.controller;

import com.liam.spring.jpa.postgresql.model.TaskDTO;
import com.liam.spring.jpa.postgresql.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for handling Tasks.
 */
@RestController
public class TaskController {

    private final TaskServices taskService;

    /**
     * Constructor to inject TaskServices dependency.
     *
     * @param taskService The TaskServices instance used for handling task-related operations.
     */
    @Autowired
    public TaskController(TaskServices taskService) {
        this.taskService = taskService;
    }

    /**
     * Retrieves all tasks in descending order.
     *
     * @return Iterable collection of TaskDTOs.
     */
    @GetMapping("/tasks")
    public Iterable<TaskDTO> fetchAllTasks() {
        return this.taskService.getAllTasksDesc();
    }

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param taskId The UUID of the task to retrieve.
     * @return ResponseEntity containing the retrieved task or a 404 status if not found.
     */
    @GetMapping("tasks/id/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable(name = "taskId") UUID taskId) {
        Optional<TaskDTO> task = taskService.getTaskById(taskId);
        return task.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    /**
     * Adds a new task.
     *
     * @param task The TaskDTO to be added.
     * @return The added TaskDTO.
     */
    @PostMapping("/tasks/add")
    public TaskDTO addTask(@RequestBody TaskDTO task) {
        return this.taskService.saveTask(task);
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param taskId The UUID of the task to be deleted.
     * @return ResponseEntity indicating success or a 404 status if the task does not exist.
     */
    @DeleteMapping("/tasks/delete/{taskId}")
    public ResponseEntity<TaskDTO> deleteTaskById(@PathVariable(name = "taskId") UUID taskId) {
        Optional<TaskDTO> task = taskService.deleteTaskById(taskId);
        return task.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    /**
     * Replaces an existing task with a new one.
     *
     * @param taskId  The UUID of the task to be replaced.
     * @param taskNew The new Task entity to replace the existing one.
     * @return ResponseEntity containing the replaced task or a 404 status if the original task is not found.
     */
    @PatchMapping("/tasks/update/{taskId}")
    public ResponseEntity<TaskDTO> changeTask(@PathVariable(name = "taskId") UUID taskId,
                                           @RequestBody Map<String, Object> updates) {
        Optional<TaskDTO > task = this.taskService.changeTask(taskId, updates);
        return task.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }
    
   
}