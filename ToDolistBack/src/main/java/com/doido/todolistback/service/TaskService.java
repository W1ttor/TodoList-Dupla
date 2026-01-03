package com.doido.todolistback.service;

import java.util.List;

import com.doido.todolistback.entity.dtos.post.PostTaskDto;
import com.doido.todolistback.entity.dtos.request.RequestTaskDto;

public interface TaskService {
    PostTaskDto addTask(PostTaskDto task);
    List<RequestTaskDto> findAll();
    PostTaskDto updateTask(Long id, PostTaskDto task);
    void deleteTask(Long id);
}
