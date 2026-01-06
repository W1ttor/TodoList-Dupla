package com.doido.todolistback.domain.task.servicies.impl;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.task.servicies.TaskService;
import com.doido.todolistback.domain.task.shared.mapper.TaskMapper;
import com.doido.todolistback.infra.repositories.TaskRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.TaskNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImple implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public PostTaskDto addTask(PostTaskDto task){

        Task taskEntity = taskMapper.toTaskPost(task);
        taskEntity.setCompleted(false);

        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public PostTaskDto updateTask(Long id, PostTaskDto task){

        Task taskEntity = taskRepository.findById(id)
                            .orElseThrow(()->new TaskNotFound());

        if (task.getTitle() != null && !task.getTitle().equals("")){
            taskEntity.setTitle(task.getTitle());
        }
        if (task.getDescription() != null && !task.getDescription().equals("")){
            taskEntity.setDescription(task.getDescription());
        }

        taskRepository.save(taskEntity);

        return taskMapper.toPostTaskDto(taskEntity);
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFound());
        taskRepository.deleteById(id);
    }

    @Override
    public List<RequestTaskDto> findAll(){
        List<Task> task = taskRepository.findAll();

        List<RequestTaskDto> requestTaskDto = task.stream()
            .map(taskMapper::toRequestTaskDto)
            .toList();

        return requestTaskDto;
    }
}
