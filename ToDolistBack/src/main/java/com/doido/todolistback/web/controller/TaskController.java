package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;
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
    public ResponseEntity<com.doido.todolistback.domain.task.dtos.request.TaskRequest> addTask(@Valid @RequestBody com.doido.todolistback.domain.task.dtos.request.TaskRequest task){

        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<com.doido.todolistback.domain.task.dtos.request.TaskRequest> updateTask(@PathVariable UUID id, @Valid @RequestBody com.doido.todolistback.domain.task.dtos.request.TaskRequest task){
        return  new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }


    @PutMapping("/{id}/completed")
    public ResponseEntity<TaskResponse> completed(boolean status, @PathVariable UUID id){
        return new ResponseEntity<>(taskService.statusTask(status, id), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> findUserTask(){
        return new ResponseEntity<>(taskService.findUserTask(), HttpStatus.OK);
    }

    //SubTask

    @PostMapping("/subtask")
    public ResponseEntity<SubTaskResponse> addSubtask(@Valid @RequestBody com.doido.todolistback.domain.task.dtos.request.SubTaskRequest subTaskDto, UUID idTarefa){
        return new ResponseEntity<>(taskService.addSubTask(subTaskDto, idTarefa), HttpStatus.CREATED);
    }

    @GetMapping("/subtask")
    public ResponseEntity<List<SubTaskResponse>> findSubtask(UUID idTarefa){
        return new ResponseEntity<>(taskService.findSubTarefa(idTarefa), HttpStatus.OK);
    }

}
