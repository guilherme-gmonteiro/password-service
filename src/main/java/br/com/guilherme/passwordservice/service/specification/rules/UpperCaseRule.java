package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

@Component
public class UpperCaseRule implements PasswordRuleInterface {
    @Override
    public boolean isSatisfiedBy(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }
}
