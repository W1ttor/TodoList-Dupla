package com.doido.todolistback.domain.task.dtos.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestTaskDto {

    private String title;
    private String description;
    private Boolean completed;
}


