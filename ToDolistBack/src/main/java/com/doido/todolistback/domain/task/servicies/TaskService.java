package com.doido.todolistback.domain.task.servicies;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    PostTaskDto addTask(PostTaskDto task);
    List<RequestTaskDto> findUserTask();
    PostTaskDto updateTask(UUID id, PostTaskDto task);
    void deleteTask(UUID id);

}
