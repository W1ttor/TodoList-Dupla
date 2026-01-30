
package com.doido.todolistback.domain.task.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "O titulo nao pode ser vazio")
    private String title;

    @NotBlank(message = "A descricao nao pode ser vazia")
    private String description;
}
