package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

@Component
public interface PasswordRuleInterface {

    boolean isSatisfiedBy(String password);

}
