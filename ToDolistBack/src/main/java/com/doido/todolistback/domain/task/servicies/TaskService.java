package com.doido.todolistback.domain.task.servicies;

import com.doido.todolistback.domain.task.dtos.post.PostSubTaskDto;
import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestSubTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    //TASK

    PostTaskDto addTask(PostTaskDto task);
    RequestTaskDto statusTask(boolean status, UUID TaskId);
    PostTaskDto updateTask(UUID id, PostTaskDto task);
    List<RequestTaskDto> findUserTask();


    void deleteTask(UUID id);

    //SUBTASK
    RequestSubTaskDto addSubTask(PostSubTaskDto subTask, UUID idTarefa);
    List<RequestSubTaskDto> findSubTarefa(UUID id);




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
