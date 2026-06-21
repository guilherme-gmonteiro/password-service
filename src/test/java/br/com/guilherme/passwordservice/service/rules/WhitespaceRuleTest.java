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
                arguments("Abcde 1!@",  false, "space in the middle"),
                arguments("Abcde1!@\t", false, "tab character"),
                arguments("Abcde1!@\n", false, "newline character"),
                arguments(" Abcde1!@",  false, "leading space"),
                arguments("Abcde1!@ ",  false, "trailing space"),
                arguments("Abcde12!@",  true,  "no whitespace"),
                arguments("",           true,  "empty string (no whitespace)")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectWhitespace(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}