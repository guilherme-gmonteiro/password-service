package br.com.guilherme.passwordservice.service;

import br.com.guilherme.passwordservice.service.specification.rules.PasswordRuleInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PassWordService {

    private final List<PasswordRuleInterface> rules;

    public boolean validatePassWord(String password) {
        if (password == null) {
            return false;
        }else {
            return rules.stream().allMatch(rule -> rule.isSatisfiedBy(password));
        }
    }
}
