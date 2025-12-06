package com.doido.todolistback.controller;

import com.doido.todolistback.entity.dtos.request.TaskDto;
import com.doido.todolistback.service.ImplService.TaskServiceImple;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto task){
        return taskService.updateTask(id ,task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<TaskDto> findAll(){
        return taskService.findAll();
    }


}
