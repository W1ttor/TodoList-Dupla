package com.doido.todolistback.service;

import java.util.List;

import com.doido.todolistback.entity.dtos.request.TaskDto;

public interface TaskService {
    TaskDto addTask(TaskDto task);
    List<TaskDto> findAll();
    TaskDto updateTask(Long id, TaskDto task);
    void deleteTask(Long id);
}
