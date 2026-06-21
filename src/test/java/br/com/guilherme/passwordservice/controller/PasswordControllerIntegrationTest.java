package br.com.guilherme.passwordservice.controller;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PasswordControllerIntegrationTest {

    private static final String ENDPOINT = "/password/validate";

    @Autowired
    private WebTestClient webTestClient;


    static Stream<Arguments> passwords() {
        return Stream.of(
                arguments("AbcDe1!@#", true,  "valid complete password"),
                arguments("Ab1!",      false, "too short"),
                arguments("abcde1!@#", false, "missing uppercase letter"),
                arguments("ABCDE1!@#", false, "missing lowercase letter"),
                arguments("AbcDefg!@", false, "missing digit"),
                arguments("AbcDe1234", false, "missing special character"),
                arguments("AAbcD1!@#", false, "repeated character"),
                arguments("AbcD 1!@#", false, "contains whitespace")
        );
    }

    @ParameterizedTest(name = "{2} -> valid={1}")
    @MethodSource("passwords")
    void shouldValidatePasswordThroughHttp(String password, boolean expectedValid, String description) {
        webTestClient.post()
                .uri(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PasswordRequest(password))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isEqualTo(expectedValid);
    }

    @Test
    void shouldRejectMalformedJson() {
        webTestClient.post()
                .uri(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"password\": ")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldHandleNullPassword() {
        webTestClient.post()
                .uri(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PasswordRequest(null))
                .exchange()
                .expectStatus().isBadRequest();
    }

    record PasswordRequest(String password) {
    }
}