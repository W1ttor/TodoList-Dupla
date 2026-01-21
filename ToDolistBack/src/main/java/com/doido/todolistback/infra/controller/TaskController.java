package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.task.dtos.post.PostSubTaskDto;
import com.doido.todolistback.domain.task.dtos.post.PostTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestSubTaskDto;
import com.doido.todolistback.domain.task.dtos.request.RequestTaskDto;
import com.doido.todolistback.domain.task.servicies.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('USER')")
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
                .ok(taskService.updateTask(id , task));
    }


    @PutMapping("/{id}/completed")
    public ResponseEntity<RequestTaskDto> completed(boolean status, @PathVariable UUID id){
        return new ResponseEntity<>(taskService.statusTask(status, id), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping()
    public ResponseEntity<List<RequestTaskDto>> findUserTask(){

        if (taskService.findUserTask().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.findUserTask());
    }

    //SubTask

    @PostMapping("/subtask")
    public ResponseEntity<RequestSubTaskDto> addSubtask(@Valid @RequestBody PostSubTaskDto subTaskDto, UUID idTarefa){
        return new ResponseEntity<>(taskService.addSubTask(subTaskDto, idTarefa), HttpStatus.CREATED);
    }

    @GetMapping("/subtask")
    public ResponseEntity<List<RequestSubTaskDto>> findSubtask(UUID idTarefa){
        return new ResponseEntity<>(taskService.findSubTarefa(idTarefa), HttpStatus.OK);
    }

}
