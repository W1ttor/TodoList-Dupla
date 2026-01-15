package com.doido.todolistback.domain.task.servicies.impl;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.task.servicies.TaskService;
import com.doido.todolistback.domain.task.shared.mapper.TaskMapper;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.infra.repositories.TaskRepository;
import com.doido.todolistback.infra.repositories.UserRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.TaskNotFound;
import com.doido.todolistback.shared.exception.CustomsExceptions.UserNotFoundException;
import com.doido.todolistback.shared.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImple implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public PostTaskDto addTask(PostTaskDto task){
        Task taskEntity = taskMapper.toTaskPost(task);

        taskEntity.setUser((User) SecurityUtils.getUser());

        taskEntity.setCompleted(false);

        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public PostTaskDto updateTask(UUID id, PostTaskDto task){

        User user = (User) userRepository
                .findByEmail(SecurityUtils.getAuthenticationEmail());

        Task taskEntity = taskRepository
                .findById(id)
                .orElseThrow(()->new TaskNotFound());

        if (taskEntity.getUser().equals(user)){
            if (task.getTitle() != null && !task.getTitle().equals("")){
                taskEntity.setTitle(task.getTitle());
            }
            if (task.getDescription() != null && !task.getDescription().equals("")){
                taskEntity.setDescription(task.getDescription());
            }

            taskRepository.save(taskEntity);

            return taskMapper.toPostTaskDto(taskEntity);
        } else {
            throw new UserNotFoundException("");
        }
    }

    @Override
    public void deleteTask(UUID id){

        User user = (User) SecurityUtils.getUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFound());

        if (task.getUser().getId().equals(user.getId())){
            taskRepository.deleteById(id);
        } else {throw new UserNotFoundException("The user is not the owner of the task.");}



    }

    @Override
    public List<RequestTaskDto> findUserTask(){

        User user = (User) SecurityUtils.getUser();

        List<Task> task = taskRepository.findByUserId(user.getId());

        List<RequestTaskDto> requestTaskDto = task.stream()
            .map(taskMapper::toRequestTaskDto)
            .toList();

        return requestTaskDto;
    }
}
