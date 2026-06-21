package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

@Component
public class MinLengthRule implements PasswordRuleInterface {

    private static final int MIN_LENGTH = 9;

    @Override
    public boolean isSatisfiedBy(String password) {
        return password != null && password.length() >= MIN_LENGTH;
    }
}
