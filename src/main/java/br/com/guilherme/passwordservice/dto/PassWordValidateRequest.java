package br.com.guilherme.passwordservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PassWordValidateRequest {
    @NotNull
    private String password;
}
