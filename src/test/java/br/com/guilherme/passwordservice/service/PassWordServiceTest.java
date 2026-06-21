package br.com.guilherme.passwordservice.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import br.com.guilherme.passwordservice.service.specification.rules.PasswordRuleInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PassWordServiceTest {

    private static final String PASSWORD = "anyPassword";

    static Stream<Arguments> ruleCombinations() {
        return Stream.of(
                Arguments.of(List.of(pass(), pass()), true),
                Arguments.of(List.of(pass(), fail()), false),
                Arguments.of(List.of(fail(), pass()), false),
                Arguments.of(List.of(fail(), fail()), false)
        );
    }

    @ParameterizedTest(name = "esperado={1}")
    @MethodSource("ruleCombinations")
    void shouldAggregateRulesCorrectly(List<PasswordRuleInterface> rules, boolean expected) {
        PassWordService service = new PassWordService(rules);

        assertTrue(expected == service.validatePassWord(PASSWORD));
    }


    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        PassWordService service = new PassWordService(List.of(pass()));

        assertFalse(service.validatePassWord(null));
    }

    @Test
    void shouldReturnTrueWhenRuleListIsEmpty() {
        PassWordService service = new PassWordService(List.of());

        assertTrue(service.validatePassWord(PASSWORD));
    }

    @Test
    void shouldStopEvaluatingAfterFirstFailingRule() {
        PasswordRuleInterface failing = mock(PasswordRuleInterface.class);
        PasswordRuleInterface laterRule = mock(PasswordRuleInterface.class);
        when(failing.isSatisfiedBy(PASSWORD)).thenReturn(false);

        PassWordService service = new PassWordService(List.of(failing, laterRule));

        assertFalse(service.validatePassWord(PASSWORD));
        verify(laterRule, never()).isSatisfiedBy(any());
    }

    @Test
    void shouldPassPasswordValueToEveryRuleWhenAllPass() {
        PasswordRuleInterface first = mock(PasswordRuleInterface.class);
        PasswordRuleInterface second = mock(PasswordRuleInterface.class);
        when(first.isSatisfiedBy(PASSWORD)).thenReturn(true);
        when(second.isSatisfiedBy(PASSWORD)).thenReturn(true);

        PassWordService service = new PassWordService(List.of(first, second));

        service.validatePassWord(PASSWORD);

        verify(first).isSatisfiedBy(PASSWORD);
        verify(second).isSatisfiedBy(PASSWORD);
    }

    private static PasswordRuleInterface pass() {
        return password -> true;
    }

    private static PasswordRuleInterface fail() {
        return password -> false;
    }
}