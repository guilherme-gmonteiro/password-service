package br.com.guilherme.passwordservice.service.specification.rules;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RepeatedCharsRule implements PasswordRuleInterface {

    @Override
    public boolean isSatisfiedBy(String password) {
        Set<Character> seen = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }
}
