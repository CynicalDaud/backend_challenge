package com.liam.spring.jpa.postgresql.services;

import com.liam.spring.jpa.postgresql.model.Task;
import com.liam.spring.jpa.postgresql.model.TaskDTO;
import com.liam.spring.jpa.postgresql.model.TaskUpdateDTO;
import com.liam.spring.jpa.postgresql.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for managing Task entities.
 */
@Service
public class TaskServices {

	private final TaskRepository taskRepository;
	final Logger logger = LoggerFactory.getLogger(TaskServices.class);

	@Autowired
	public TaskServices(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	/**
	 * Retrieves all tasks ordered by creation date in descending order.
	 *
	 * @return List of tasks ordered by creation date (newest first).
	 */
	public List<TaskDTO> getAllTasksDesc() {
		logger.info("\n Getting all tasks \n");
		return taskRepository.findAllOrderByCreatedAtDesc().stream()
							 .map(TaskDTO::new).collect(Collectors
							 .toList());
	}

	/**
	 * Retrieves a task by its unique identifier.
	 *
	 * @param taskId The UUID of the task to retrieve.
	 * @return Optional containing the retrieved task, or empty if not found.
	 */
	public Optional<TaskDTO> getTaskById(UUID taskId) {
		logger.info("\n Getting task with ID: " + taskId + "\n");
		return taskRepository.findById(taskId).map(TaskDTO::new);
	}

	/**
	 * Saves a task to the database.
	 *
	 * @param  task    The task to be saved.
	 * @return taskDTO containing the saved task.
	 */
	public TaskDTO saveTask(TaskDTO taskDTO) {
		logger.info("\n Saving task with ID: " + taskDTO.getId() + "\n");
		// Check if a task with the given ID already exists
		Optional<Task> existingTask = taskRepository.findById(taskDTO.getId());
		if (existingTask.isPresent()) {
			// Throw an exception or return an error message
			throw new IllegalArgumentException("A task with the given ID already exists.");
		}
		Task taskEntity = taskDTO.toEntity();
		taskRepository.save(taskEntity);
		return taskEntity.toDTO();
	}

	/**
	 * Deletes a task from the database by its unique identifier.
	 *
	 * @param  taskId   The UUID of the task to be deleted.
	 * @return Optional containing the deleted task, or empty if not found.
	 */
	public Optional<TaskDTO> deleteTaskById(UUID taskId) {
		logger.info("\n Deleting task with ID: " + taskId);
		// Try get task
		Optional<Task> task = taskRepository.findById(taskId);
		// Remove if exists
		task.ifPresent(t -> {
			taskRepository.deleteById(taskId);
			logger.info("Task deleted \n");
		});
		return task.map(TaskDTO::new);
	}

	/**
	 * Replaces an existing task with a new one, maintaining the original creation
	 * date. Since the repository extends the @class CrudRepository, the save()
	 * method overwrites entries and so acts as a replace.
	 *
	 * @param  taskId   The UUID of the task to be replaced.
	 * @param  taskNew  The new task.
	 * @return Optional containing the updated task, or empty if the original task
	 *         was not found.
	 */
	public Optional<TaskDTO> changeTask(UUID taskId, TaskUpdateDTO updates) {
		Optional<Task> taskEntity = taskRepository.findById(taskId);
		return taskEntity.map(task -> {
			if (updates.getTitle() != null) {
				task.setTitle(updates.getTitle());
			}
			if (updates.getDescription() != null) {
				task.setDescription(updates.getDescription());
			}
			if (updates.getPriority() != null) {
				task.setPriority(updates.getPriority());
			}
			if (updates.getStatus() != null) {
				task.setStatus(updates.getStatus());
			}
			if (updates.getDueDate() != null) {
				task.setDueDate(updates.getDueDate());
			}
			if (updates.getResolvedAt() != null) {
				task.setResolvedAt(updates.getResolvedAt());
			}
	        // Update dateModified to current date
	        task.setUpdatedAt(LocalDateTime.now());
			taskRepository.save(task);
			return Optional.of(task.toDTO());
		}).orElse(Optional.empty());
	}	
}