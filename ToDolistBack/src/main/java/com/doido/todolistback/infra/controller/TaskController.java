package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import com.doido.todolistback.domain.task.servicies.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public PostTaskDto addTask(@Valid @RequestBody PostTaskDto task){
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public PostTaskDto updateTask(@PathVariable Long id, @Valid @RequestBody PostTaskDto task){
        return taskService.updateTask(id ,task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id){
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<RequestTaskDto> findAll(){
        return taskService.findAll();
    }

}
