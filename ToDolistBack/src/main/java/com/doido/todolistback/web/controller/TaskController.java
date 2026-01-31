package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.task.dtos.request.SubTaskRequest;
import com.doido.todolistback.domain.task.dtos.request.TaskRequest;
import com.doido.todolistback.domain.task.dtos.response.SubTaskResponse;
import com.doido.todolistback.domain.task.dtos.response.TaskResponse;
import com.doido.todolistback.domain.task.servicies.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@Tag(name = "Tarefas", description = "Controlador de tarefas")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Registrar um tarefa", description = "Ao enviar os dados ele registra a tarefa no banco")
    @PostMapping
    public ResponseEntity<TaskRequest> addTask(@Valid @RequestBody TaskRequest task){

        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);

    }

    @Operation(summary = "Atualiza o campo das tarefas", description = "Ao enviar os dados, ele atualiza a tarefa no banco")
    @PutMapping("/{id}")
    public ResponseEntity<TaskRequest> updateTask(@PathVariable UUID id, @Valid @RequestBody com.doido.todolistback.domain.task.dtos.request.TaskRequest task){
        return  new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }


    @Operation(summary = "Concluir tarefa", description = "O usuario pode concluir sua tarefa")
    @PutMapping("/{id}/completed")
    public ResponseEntity<TaskResponse> completed(boolean status, @PathVariable UUID id){
        return new ResponseEntity<>(taskService.statusTask(status, id), HttpStatus.OK);
    }


    @Operation(summary = "Deletar tarefa", description = "O usuario pode deletar sua tarefa")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna Task", description = "retorna todas as task do usuario")
    @GetMapping()
    public ResponseEntity<List<TaskResponse>> findUserTask(){
        return new ResponseEntity<>(taskService.findUserTask(), HttpStatus.OK);
    }

    @Operation(summary = "Paginacao de Task", description = "Entregue um valor de desaja de itens, e a pagina que deseja")
    @GetMapping("/page")
    public ResponseEntity<Page<TaskResponse>> findAllTaskByUserId(Pageable pageable, @RequestParam(required = false ) Boolean completed){
        return new ResponseEntity<>(taskService.filter(completed ,pageable), HttpStatus.OK);
    }

    //SubTask

    @Operation(summary = "Registrar uma subtask", description = "Adiciona uma subtask na task")
    @PostMapping("/subtask")
    public ResponseEntity<SubTaskResponse> addSubtask(@Valid @RequestBody SubTaskRequest subTaskDto, UUID idTarefa){
        return new ResponseEntity<>(taskService.addSubTask(subTaskDto, idTarefa), HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna subtasks", description = "Retorna todas as subtask da task")
    @GetMapping("/subtask")
    public ResponseEntity<List<SubTaskResponse>> findSubtask(UUID idTarefa){
        return new ResponseEntity<>(taskService.findSubTarefa(idTarefa), HttpStatus.OK);
    }

}
