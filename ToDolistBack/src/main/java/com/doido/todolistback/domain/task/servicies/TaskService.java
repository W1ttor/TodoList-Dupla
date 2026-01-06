package com.doido.todolistback.domain.task.servicies;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;

import java.util.List;

public interface TaskService {
    PostTaskDto addTask(PostTaskDto task);
    List<RequestTaskDto> findAll();
    PostTaskDto updateTask(Long id, PostTaskDto task);
    void deleteTask(Long id);
}
