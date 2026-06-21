package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.UpperCaseRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UpperCaseRuleTest {

    private final UpperCaseRule rule = new UpperCaseRule();


    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("Abcde123!", true,  "one uppercase letter"),
                arguments("ABCde123!", true,  "multiple uppercase letters"),
                arguments("abcde123!", false, "missing uppercase letter"),
                arguments("abcdefghi", false, "all lowercase"),
                arguments("",          false, "empty string")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectUpperCase(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}