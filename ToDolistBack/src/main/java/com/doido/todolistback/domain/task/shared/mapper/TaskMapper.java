package com.doido.todolistback.domain.task.shared.mapper;


import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;
import com.doido.todolistback.domain.task.entity.SubTask;
import com.doido.todolistback.domain.task.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    com.doido.todolistback.domain.task.dtos.request.TaskRequest taskToPostTaskDto(Task task);
    Task postTaskDtoToTask(com.doido.todolistback.domain.task.dtos.request.TaskRequest taskDto);

    TaskResponse taskToRequestTaskDto(Task task);
    Task requestTaskDtoToTask(TaskResponse taskDto);

    com.doido.todolistback.domain.task.dtos.request.SubTaskRequest subTaskToPostSubTaskDto(SubTask subTask);
    SubTask  postDtotoSubTask(com.doido.todolistback.domain.task.dtos.request.SubTaskRequest subTaskRequest);

    SubTaskResponse subTaskToRequestDto(SubTask subTask);
    SubTask requestSubTaskDtoToSubTasK(TaskResponse taskResponse);

}

