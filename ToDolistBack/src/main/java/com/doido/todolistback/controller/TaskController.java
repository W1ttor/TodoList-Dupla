package com.doido.todolistback.controller;

import com.doido.todolistback.entity.dtos.TaskDto;
import com.doido.todolistback.service.ImplService.TaskServiceImple;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImple taskService;

    @PostMapping
    public TaskDto addTask(@Valid @RequestBody TaskDto task){
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@Valid @RequestBody TaskDto task){
        return taskService.updateTask(task);
    }

}
