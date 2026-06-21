package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.WhitespaceRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WhitespaceRuleTest {

    private final WhitespaceRule rule = new WhitespaceRule();

    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("Abcde 1!@",  false, "espaço no meio"),
                arguments("Abcde1!@\t", false, "tab"),
                arguments("Abcde1!@\n", false, "newline"),
                arguments(" Abcde1!@",  false, "espaço no início"),
                arguments("Abcde1!@ ",  false, "espaço no fim"),
                arguments("Abcde12!@",  true,  "sem whitespace"),
                arguments("",           true,  "vazia (sem whitespace)")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectWhitespace(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}