package com.doido.todolistback.domain.task.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubTaskResponse {

    private UUID id;
    private String nameSubTask;
    private Boolean completed;

}
