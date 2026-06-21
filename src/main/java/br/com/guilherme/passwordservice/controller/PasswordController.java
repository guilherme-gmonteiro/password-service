package br.com.guilherme.passwordservice.controller;

import br.com.guilherme.passwordservice.dto.PassWordValidateRequest;
import br.com.guilherme.passwordservice.dto.PassWordValidateResponse;
import br.com.guilherme.passwordservice.service.PassWordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/password")
@AllArgsConstructor
public class PasswordController {

    private final PassWordService passWordService;

    @PostMapping("/validate")
    public Mono<PassWordValidateResponse> validatePassWord(@Valid @RequestBody Mono<PassWordValidateRequest> request){

        return request
                .map(passwordRequest ->
                        new PassWordValidateResponse(
                                passWordService.validatePassWord(passwordRequest.getPassword())
                        ));
    }
}
