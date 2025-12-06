package com.doido.todolistback.service.ImplService;

import com.doido.todolistback.exception.CustomsExceptions.TaskNotFound;
import com.doido.todolistback.entity.Task;
import com.doido.todolistback.entity.dtos.request.TaskDto;
import com.doido.todolistback.mapper.TaskMapper;
import com.doido.todolistback.repository.TaskRepository;
import com.doido.todolistback.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImple implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskDto addTask(TaskDto task){

        Task taskEntity = taskMapper.toTask(task);
        taskEntity.setCompleted(false);

        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto task){

        Task taskEntity = taskRepository.findById(id)
                            .orElseThrow(()->new TaskNotFound());

        if (task.getTitle() != null && !task.getTitle().equals("")){
            taskEntity.setTitle(task.getTitle());
        }
        if (task.getDescription() != null && !task.getDescription().equals("")){
            taskEntity.setDescription(task.getDescription());
        }

        taskRepository.save(taskEntity);

        return taskMapper.toTaskDto(taskEntity);
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFound());
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> findAll(){
        List<Task> task = taskRepository.findAll();

        List<TaskDto> taskdto = task.stream()
            .map(taskMapper::toTaskDto)
            .toList();

        return taskdto;
    }
}
