package com.doido.todolistback.controller;

import com.doido.todolistback.entity.dtos.TaskDto;
import com.doido.todolistback.entity.Task;
import com.doido.todolistback.service.ImplService.TaskServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private TaskServiceImple taskService;

    @PostMapping
    public TaskDto addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
}
