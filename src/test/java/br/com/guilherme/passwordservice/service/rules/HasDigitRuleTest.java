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
                arguments("1Password", true,  "dígito no início"),
                arguments("Pass1word", true,  "dígito no meio"),
                arguments("Password9", true,  "dígito no fim"),
                arguments("123456789", true,  "apenas dígitos"),
                arguments("Password",  false, "sem dígito"),
                arguments("",          false, "string vazia")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectDigit(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}