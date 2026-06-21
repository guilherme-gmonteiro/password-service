package br.com.guilherme.passwordservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassWordValidateResponse {
    private boolean isValid;
}
