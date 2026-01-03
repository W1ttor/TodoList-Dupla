package com.doido.todolistback.entity.dtos.request;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestTaskDto {

    @NotBlank(message = "O titulo nao pode ser vazio")
    private String title;

    @NotBlank(message = "A descricao nao pode ser vazia")
    private String description;

    private Boolean completed;
}


