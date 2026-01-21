package com.doido.todolistback.domain.user.dtos.post;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePasswordUser {

    @NotBlank(message = "old password not entered")
    private String oldPassword;

    @NotBlank(message = "new password not entered")
    private String newPassword;

}
