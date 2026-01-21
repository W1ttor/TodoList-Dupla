package com.doido.todolistback.domain.task.shared.mapper;


import com.doido.todolistback.domain.task.dtos.post.PostSubTaskDto;
import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestSubTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import com.doido.todolistback.domain.task.entity.SubTask;
import com.doido.todolistback.domain.task.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    PostTaskDto taskToPostTaskDto(Task task);
    Task postTaskDtoToTask(PostTaskDto taskDto);

    RequestTaskDto taskToRequestTaskDto(Task task);
    Task requestTaskDtoToTask(RequestTaskDto taskDto);

    PostSubTaskDto subTaskToPostSubTaskDto(SubTask subTask);
    SubTask  postDtotoSubTask(PostSubTaskDto postSubTaskDto);

    RequestSubTaskDto subTaskToRequestDto(SubTask subTask);
    SubTask requestSubTaskDtoToSubTasK(RequestTaskDto requestTaskDto);

}

