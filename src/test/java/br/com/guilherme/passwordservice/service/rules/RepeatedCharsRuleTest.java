package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.RepeatedCharsRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RepeatedCharsRuleTest {

    private final RepeatedCharsRule rule = new RepeatedCharsRule();


    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("Abbcde12!", false, "minúscula repetida (adjacente)"),
                arguments("AAcde12!@", false, "maiúscula repetida (adjacente)"),
                arguments("Abcde112!", false, "dígito repetido"),
                arguments("Abcde12!!", false, "especial repetido"),
                arguments("abcdea12!", false, "repetição NÃO-adjacente"),
                arguments("AbcDe12!@", true,  "todos os caracteres únicos"),
                arguments("Aabcde1!@", true,  "'A' e 'a' são distintos (case-sensitive)"),
                arguments("",          true,  "vazia (vacuamente sem repetição)")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectRepeatedChars(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}