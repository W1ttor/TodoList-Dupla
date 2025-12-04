package com.doido.todolistback.entity.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    @Max(50)
    @NotBlank(message = "O titulo nao pode ser vazio")
    private String title;

    @NotBlank(message = "A descricao nao pode ser vazia")
    private String description;

    private Boolean completed;
}
