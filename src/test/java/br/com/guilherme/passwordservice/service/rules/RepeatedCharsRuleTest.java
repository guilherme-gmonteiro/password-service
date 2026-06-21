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
                arguments("Abbcde12!", false, "repeated lowercase letter (adjacent)"),
                arguments("AAcde12!@", false, "repeated uppercase letter (adjacent)"),
                arguments("Abcde112!", false, "repeated digit"),
                arguments("Abcde12!!", false, "repeated special character"),
                arguments("abcdea12!", false, "non-adjacent repetition"),
                arguments("AbcDe12!@", true,  "all characters are unique"),
                arguments("Aabcde1!@", true,  "'A' and 'a' are distinct (case-sensitive)"),
                arguments("",          true,  "empty string (vacuously no repetition)")
        );
    }

    @ParameterizedTest(name = "{2}: \"{0}\" -> {1}")
    @MethodSource("passwords")
    void shouldDetectRepeatedChars(String password, boolean expected, String description) {
        assertEquals(expected, rule.isSatisfiedBy(password));
    }
}