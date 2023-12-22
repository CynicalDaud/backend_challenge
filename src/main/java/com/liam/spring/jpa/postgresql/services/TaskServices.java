package com.liam.spring.jpa.postgresql.services;

import com.liam.spring.jpa.postgresql.model.Task;
import com.liam.spring.jpa.postgresql.model.TaskDTO;
import com.liam.spring.jpa.postgresql.repository.TaskRepository;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for managing Task entities.
 */
@Service
public class TaskServices {

	private final TaskRepository taskRepository;

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
		System.out.println("\n Getting all tasks \n");
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
		System.out.println("\n Getting task with ID: " + taskId + "\n");
		return taskRepository.findById(taskId).map(TaskDTO::new);
	}

	/**
	 * Saves a task to the database.
	 *
	 * @param  task    The task to be saved.
	 * @return taskDTO containing the saved task.
	 */
	public TaskDTO saveTask(TaskDTO taskDTO) {
		System.out.println("\n Saving task with ID: " + taskDTO.getId() + "\n");
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
		System.out.println("\n Deleting task with ID: " + taskId);
		// Try get task
		Optional<Task> task = taskRepository.findById(taskId);
		// Remove if exists
		task.ifPresent(t -> {
			taskRepository.deleteById(taskId);
			System.out.println("Task deleted \n");
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
	public Optional<TaskDTO> changeTask(UUID taskId, Map<String, Object> updates) {
		System.out.println("\n Replacing task with ID: " + taskId);
		// Get current task from DB
		Optional<Task> taskOld = taskRepository.findById(taskId);
		return taskOld.map(task -> {

			// Copy non-null properties from updates to the existing task
			applyUpdates(task, updates);

			// Save the updated task
			taskRepository.save(task);

			return Optional.of(task.toDTO());
		}).orElse(Optional.empty());
	}

	// Utility method to apply updates to an object
	private void applyUpdates(Object target, Map<String, Object> updates) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(target);
		MutablePropertyValues propertyValues = new MutablePropertyValues(updates);
		beanWrapper.setPropertyValues(propertyValues, true, true);
	}
}