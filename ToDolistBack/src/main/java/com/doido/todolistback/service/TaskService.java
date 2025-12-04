package com.doido.todolistback.service;

import com.doido.todolistback.entity.Task;
import com.doido.todolistback.entity.dtos.TaskDto;

public interface TaskService {
    TaskDto addTask(TaskDto task);
    TaskDto updateTask(TaskDto task);
}
