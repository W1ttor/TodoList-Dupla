package com.doido.todolistback.domain.task.dtos.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RequestTaskDto {

    private UUID id;
    private String title;
    private String description;
    private Boolean completed;

}


