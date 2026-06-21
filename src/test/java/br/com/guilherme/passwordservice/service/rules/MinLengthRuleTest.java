package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.MinLengthRule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MinLengthRuleTest {

    private final MinLengthRule rule = new MinLengthRule();

    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("Abc1!xy",     false, "7 caracteres (abaixo)"),
                arguments("Abcde12!",    false, "8 caracteres (um abaixo do limite)"),
                arguments("Abcde123!",   true,  "9 caracteres (exatamente no limite)"),
                arguments("Abcde12345!", true,  "11 caracteres (acima)"),
                arguments("",            false, "string vazia")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldValidateLength(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}