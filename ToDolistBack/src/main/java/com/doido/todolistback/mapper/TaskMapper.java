package com.doido.todolistback.mapper;

import com.doido.todolistback.entity.Task;
import com.doido.todolistback.entity.dtos.post.PostTaskDto;
import com.doido.todolistback.entity.dtos.request.RequestTaskDto;

import io.micrometer.core.ipc.http.HttpSender.Request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    PostTaskDto toPostTaskDto(Task task);
    Task toTaskPost(PostTaskDto taskDto);

    RequestTaskDto toRequestTaskDto(Task task);
    Task toTaskRequest(RequestTaskDto taskDto);
}
