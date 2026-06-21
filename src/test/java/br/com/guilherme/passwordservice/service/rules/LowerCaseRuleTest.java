package br.com.guilherme.passwordservice.service.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.LowerCaseRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LowerCaseRuleTest {

    private final LowerCaseRule rule = new LowerCaseRule();


    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("ABCDe123!", true,  "one lowercase letter"),
                arguments("ABcde123!", true,  "multiple lowercase letters"),
                arguments("ABCDE123!", false, "missing lowercase letter (with digits and special character)"),
                arguments("ABCDEFGHI", false, "all uppercase"),
                arguments("",          false, "empty string")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectLowerCase(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}