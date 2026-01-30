package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.servicies.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<PostTaskDto> addTask(@Valid @RequestBody PostTaskDto task){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.addTask(task));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostTaskDto> updateTask(@PathVariable UUID id, @Valid @RequestBody PostTaskDto task){
        return  ResponseEntity
                .ok(taskService.updateTask(id ,task));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> findUserTask(){

        if (taskService.findUserTask().isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok(taskService.findUserTask());
    }

}
