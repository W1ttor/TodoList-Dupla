package com.doido.todolistback.mapper;

import com.doido.todolistback.entity.Task;
import com.doido.todolistback.entity.dtos.request.TaskDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toTaskDto(Task task);
    Task toTask(TaskDto taskDto);
}
