package com.doido.todolistback.domain.task.servicies.impl;

import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;
import com.doido.todolistback.domain.task.entity.SubTask;
import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.task.servicies.TaskService;
import com.doido.todolistback.domain.task.shared.mapper.TaskMapper;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.task.repositories.TaskRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.TaskNotFound;
import com.doido.todolistback.shared.exception.CustomsExceptions.UserNotFoundException;
import com.doido.todolistback.shared.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImple implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public com.doido.todolistback.domain.task.dtos.request.TaskRequest addTask(com.doido.todolistback.domain.task.dtos.request.TaskRequest task){
        Task taskEntity = taskMapper.postTaskDtoToTask(task);

        taskEntity.setUser((User) SecurityUtils.getUser());
        taskEntity.setCompleted(false);

        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public com.doido.todolistback.domain.task.dtos.request.TaskRequest updateTask(UUID id, com.doido.todolistback.domain.task.dtos.request.TaskRequest task){

        User user = (User) SecurityUtils.getUser();

        Task taskEntity = taskRepository
                .findById(id)
                .orElseThrow(()->new TaskNotFound());


        if (!taskEntity.getUser().equals(user)){
            throw new UserNotFoundException("");
        }


        if (task.getTitle() != null && !task.getTitle().equals("")){
            taskEntity.setTitle(task.getTitle());
        }
        if (task.getDescription() != null && !task.getDescription().equals("")){
            taskEntity.setDescription(task.getDescription());
        }

        taskRepository.save(taskEntity);

        return taskMapper.taskToPostTaskDto(taskEntity);




    }

    @Override
    public TaskResponse statusTask(boolean status, UUID TaskId){
        Task task = isOwner(TaskId);
        task.setCompleted(status);
        taskRepository.save(task);

        return taskMapper.taskToRequestTaskDto(task);
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
    public List<TaskResponse> findUserTask(){

        User user = (User) SecurityUtils.getUser();

        List<Task> task = taskRepository.findByUserId(user.getId());

        List<TaskResponse> taskResponse = task.stream()
            .map(taskMapper::taskToRequestTaskDto)
            .toList();

        return taskResponse;
    }

    //SubTask

    @Override
    public SubTaskResponse addSubTask(com.doido.todolistback.domain.task.dtos.request.SubTaskRequest subTask, UUID idTarefa) {

            Task task = isOwner(idTarefa);

            SubTask subTaskEntity = taskMapper.postDtotoSubTask(subTask);

            task.addSubTask(subTaskEntity);

            taskRepository.save(task);

            return  taskMapper.subTaskToRequestDto(subTaskEntity);
    }



    @Override
    @Transactional
    public List<SubTaskResponse> findSubTarefa(UUID id) {

        Task task = isOwner(id);

        return task.getSubTasks()
                .stream()
                .map(taskMapper::subTaskToRequestDto)
                .toList();
    }


    //Private Method

    private Task isOwner(UUID id){
        User user = (User) SecurityUtils.getUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFound());

        if (!user.getId().equals(task.getUser().getId())){
            throw new RuntimeException("User is not the owner of the task.");
        }

        return task;
    }
}
