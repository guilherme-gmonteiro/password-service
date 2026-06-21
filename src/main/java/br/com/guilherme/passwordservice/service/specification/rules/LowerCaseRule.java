package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

@Component
public class LowerCaseRule implements PasswordRuleInterface {

    @Override
    public boolean isSatisfiedBy(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }
}
