
package com.doido.todolistback.entity.dtos.post;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskDto {

    @NotBlank(message = "O titulo nao pode ser vazio")
    private String title;

    @NotBlank(message = "A descricao nao pode ser vazia")
    private String description;
}
