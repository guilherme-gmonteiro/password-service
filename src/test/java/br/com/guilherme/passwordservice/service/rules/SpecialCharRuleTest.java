package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.guilherme.passwordservice.service.specification.rules.SpecialCharRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialCharRuleTest {

    private final SpecialCharRule rule = new SpecialCharRule();

    @ParameterizedTest
    @ValueSource(strings = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_",
            "=", "+", "[", "]", "{", "}", ";", ":", ",", ".", "<", ">", "?"})
    void shouldAcceptEachAllowedSpecialChar(String special) {
        assertTrue(rule.isSatisfiedBy("Abcde123" + special));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abcde1234", "ABCDE1234", "abcde1234"})
    void shouldRejectPasswordWithNoSpecialChar(String password) {
        assertFalse(rule.isSatisfiedBy(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"~", "`", "|", "\\", "/", "'", "\"", "\u20AC", "\u00A3"})
    void shouldRejectCharsOutsideAllowedSet(String notAllowed) {
        assertFalse(rule.isSatisfiedBy("Abcde123" + notAllowed));
    }

    @Test
    void shouldRejectEmptyPassword() {
        assertFalse(rule.isSatisfiedBy(""));
    }

    @Test
    void shouldThrowWhenPasswordIsNull() {
        assertThrows(NullPointerException.class, () -> rule.isSatisfiedBy(null));
    }
}