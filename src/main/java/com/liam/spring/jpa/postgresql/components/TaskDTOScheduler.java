package com.liam.spring.jpa.postgresql.components;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liam.spring.jpa.postgresql.model.TaskDTO;
import com.liam.spring.jpa.postgresql.services.TaskServices;

/**
 * Scheduler class for generating and persisting random TaskDTO objects at fixed intervals.
 */
@Component
public class TaskDTOScheduler {

    private final TaskServices taskServices;
    final Logger logger = LoggerFactory.getLogger(TaskDTOScheduler.class);


    /**
     * Constructor for TaskDTOScheduler.
     *
     * @param taskServices The TaskServices instance used for handling task-related operations.
     */
    @Autowired
    public TaskDTOScheduler(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    /**
     * Scheduled method to create and persist a random TaskDTO object every 5 seconds.
     * The method generates random data for a TaskDTO, persists it, modifies the description,
     * and persists it again to demonstrate updating an entity.
     */
    @Scheduled(fixedRate = 50000)
    public void createAndPersistRandomTask() {
        // Generate random data for a TaskDTO
        TaskDTO newTask = new TaskDTO(LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextInt(30)),
                "Random Task ", "Random description", generateRandomValue("High", "Medium", "Low"),
                generateRandomValue("Open", "In Progress", "Closed"), LocalDateTime.now(), null);

        // Change description based on the generated ID
        newTask.setDescription("Random Task " + newTask.getId().toString().substring(0, 8));

        // Persist
        newTask = taskServices.saveTask(newTask);

        logger.info("Scheduled task executed at " + LocalDateTime.now());
    }

    /**
     * Generates a random value from the given array of values.
     *
     * @param  values   The array of values to choose from.
     * @return [values] randomly selected value from the array.
     */
    private String generateRandomValue(String... values) {
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
