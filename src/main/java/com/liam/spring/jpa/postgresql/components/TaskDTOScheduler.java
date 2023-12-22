package com.liam.spring.jpa.postgresql.components;


import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liam.spring.jpa.postgresql.model.TaskDTO;
import com.liam.spring.jpa.postgresql.services.TaskServices;

@Component
public class TaskDTOScheduler {
	
    private final TaskServices taskServices;

    @Autowired
    public TaskDTOScheduler(TaskServices taskServices) {
        this.taskServices = taskServices;
    }
    
    @Scheduled(fixedRate = 5000) // Execute every 5 seconds
    public void createAndPersistRandomTask() {
        // Generate random data for a TaskDTO
        TaskDTO newTask = new TaskDTO( 
	        LocalDateTime.now().plusDays(new Random().nextInt(30)),
	        "Random Task ", 
	        "Random description", 
	        generateRandomValue("High", "Medium", "Low"),
	        generateRandomValue("Open", "In Progress", "Closed"),
	        LocalDateTime.now(),
	        null
		);
        
        // Persist
        newTask = taskServices.saveTask(newTask);

        // Change description
        newTask.setDescription("Random Task "+newTask.getId().toString().substring(0, 8));
        
        // Persist again to fix description
        newTask = taskServices.saveTask(newTask);

        System.out.println("Scheduled task executed at " + LocalDateTime.now());
    }

    private String generateRandomValue(String... values) {
        // Generate a random value from the given array of values
        return values[new Random().nextInt(values.length)];
    }
}

