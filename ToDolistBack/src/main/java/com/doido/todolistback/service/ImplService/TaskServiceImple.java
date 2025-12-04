package com.doido.todolistback.service.ImplService;

import com.doido.todolistback.entity.dtos.TaskDto;
import com.doido.todolistback.entity.Task;
import com.doido.todolistback.mapper.TaskMapper;
import com.doido.todolistback.repository.TaskRepository;
import com.doido.todolistback.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public TaskDto updateTask(TaskDto task){
        Task taskEntity = taskMapper.toTask(task);

        if (taskEntity.getTitle() != null && !taskEntity.getTitle().equals("")){
            taskEntity.setTitle(taskEntity.getTitle());
        }
        if (taskEntity.getDesc() != null && !taskEntity.getDesc().equals("")){
            taskEntity.setDesc(taskEntity.getDesc());
        }

        taskRepository.save(taskEntity);

        task = taskMapper.toTaskDto(taskEntity);

        return task;
    }
}
