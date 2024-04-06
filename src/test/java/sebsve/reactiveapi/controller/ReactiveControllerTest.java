package sebsve.reactiveapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class ReactiveControllerTest {

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(new ReactiveController()).build();
    }
    private WebTestClient webTestClient;


    @Test
    void fluxTest() {
        var flux = webTestClient.get().uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_NDJSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1, 2, 3)
                .thenCancel()
                .verify();
    }

    @Test
    void monoTest() {
        var mono = webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(mono)
                .expectSubscription()
                .expectNext(1)
                .verifyComplete();
    }
}
