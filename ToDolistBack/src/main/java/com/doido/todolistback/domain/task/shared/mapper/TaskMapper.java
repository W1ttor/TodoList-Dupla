package com.doido.todolistback.domain.task.shared.mapper;

import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    PostTaskDto toPostTaskDto(Task task);
    Task toTaskPost(PostTaskDto taskDto);

    RequestTaskDto toRequestTaskDto(Task task);
    Task toTaskRequest(RequestTaskDto taskDto);
}

