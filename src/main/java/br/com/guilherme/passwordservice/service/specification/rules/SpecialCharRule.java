package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

@Component
public class SpecialCharRule implements PasswordRuleInterface {

    private static final String SPECIALS = "!@#$%^&*()-_=+[]{};:,.<>?";

    @Override
    public boolean isSatisfiedBy(String password) {
        return password.chars().anyMatch(c -> SPECIALS.indexOf(c) >= 0);
    }
}
