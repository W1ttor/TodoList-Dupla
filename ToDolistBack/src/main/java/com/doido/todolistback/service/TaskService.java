package com.doido.todolistback.service;

import com.doido.todolistback.dtos.TaskDto;
import com.doido.todolistback.entity.Task;
import com.doido.todolistback.mapper.TaskMapper;
import com.doido.todolistback.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDto addTask(Task task){
        taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }
}
