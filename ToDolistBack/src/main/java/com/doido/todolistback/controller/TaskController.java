package com.doido.todolistback.controller;

import com.doido.todolistback.entity.dtos.post.PostTaskDto;
import com.doido.todolistback.entity.dtos.request.RequestTaskDto;
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
    public PostTaskDto addTask(@Valid @RequestBody PostTaskDto task){
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public PostTaskDto updateTask(@PathVariable Long id, @Valid @RequestBody PostTaskDto task){
        return taskService.updateTask(id ,task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<RequestTaskDto> findAll(){
        return taskService.findAll();
    }

}
