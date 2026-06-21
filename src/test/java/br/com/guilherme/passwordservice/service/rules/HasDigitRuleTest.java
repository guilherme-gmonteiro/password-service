package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.HasDigitRule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HasDigitRuleTest {

    private final HasDigitRule rule = new HasDigitRule();


    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("1Password", true,  "digit at the beginning"),
                arguments("Pass1word", true,  "digit in the middle"),
                arguments("Password9", true,  "digit at the end"),
                arguments("123456789", true,  "digits only"),
                arguments("Password",  false, "missing digit"),
                arguments("",          false, "empty string")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectDigit(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}