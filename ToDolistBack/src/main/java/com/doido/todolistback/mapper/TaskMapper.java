package com.doido.todolistback.mapper;

import com.doido.todolistback.dtos.TaskDto;
import com.doido.todolistback.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface TaskMapper {
    TaskDto toTaskDto(Task task);
    Task toTask(TaskDto taskDto);
}
