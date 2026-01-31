package com.doido.todolistback.domain.task.servicies;

import com.doido.todolistback.domain.task.dtos.request.SubTaskRequest;
import com.doido.todolistback.domain.task.dtos.request.TaskRequest;
import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    //TASK

    TaskRequest addTask(TaskRequest task);
    TaskResponse statusTask(boolean status, UUID TaskId);
    TaskRequest updateTask(UUID id, TaskRequest task);
    List<TaskResponse> findUserTask();
    Page<TaskResponse> filter(Boolean completed ,Pageable pageable);


    void deleteTask(UUID id);

    //SUBTASK
    SubTaskResponse addSubTask(SubTaskRequest subTask, UUID idTarefa);
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
