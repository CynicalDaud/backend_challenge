# Backend Challenge

This repository contains the Docker configuration files for building and running the project using Docker Compose.
Prerequisites

Before you begin, ensure that you have Docker and Docker Compose installed on your machine. You can download them from the official Docker website:

- Docker
- Docker Compose

Building the Docker Image

To build the Docker image, follow these steps:

- Clone this repository to your local machine:
- Navigate to the project directory:
- Build the Docker image using the provided Dockerfile with the image name _challenge_image_

Once the image has been built it can be run with __docker-compose up__ 


## Structure

You can find the __jar__ file within the __target__ sub-directory. Source code can be found in __src__ and according to package directory formats.

## Endpoints
- __GET .../tasks/get__: Retrieve all tasks in descending order.
- __GET .../tasks/get/{taskID}__: Retrieve task by its unique identifier.
- __POST .../tasks/add__: Add a new task. Specify the body according to the constructor parameters of __TaskDTO.java__.
  - Minimal (Due date, title, description, priority) will set fields like status, created at and updated at automatically.
  - Full (Due date, title, description, priority, status, created at, resolved at) will only set the "updated at" field dynamically (given current time).
- __DELETE .../tasks/delete/{taskID}__: Delete task by its unique identifier.
- __PATCH .../tasks/update/{taskID}__: Replace contents at task given its unique identifier. The new contents for the task should be included in the body of the request. Any number of the Task parameters can be specified for replacement. Only those non-null parameters present in the body will be changed.
