package com.doido.todolistback.domain.task.servicies;

import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    //TASK

    com.doido.todolistback.domain.task.dtos.request.TaskRequest addTask(com.doido.todolistback.domain.task.dtos.request.TaskRequest task);
    TaskResponse statusTask(boolean status, UUID TaskId);
    com.doido.todolistback.domain.task.dtos.request.TaskRequest updateTask(UUID id, com.doido.todolistback.domain.task.dtos.request.TaskRequest task);
    List<TaskResponse> findUserTask();


    void deleteTask(UUID id);

    //SUBTASK
    SubTaskResponse addSubTask(com.doido.todolistback.domain.task.dtos.request.SubTaskRequest subTask, UUID idTarefa);
    List<SubTaskResponse> findSubTarefa(UUID id);




    /*
    *
    * findAll
    * findAllByUser
    * findById
    * create
    * update
    * delete
    * disable
    *
    *
    *
    *
    *
    * */


/*
    Task findById(UUID id);
    List<Task> findAll();
    List<Task> findAllByUser(UUID idUsuario);
    Task create(Task task);
    Task update(UUID id, Task task);
    void delete(UUID id);
    void disable(UUID id);*/

}
