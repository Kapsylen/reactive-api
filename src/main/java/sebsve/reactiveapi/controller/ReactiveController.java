package sebsve.reactiveapi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Integer> getData() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                .log();
    }

    @GetMapping(path = "/mono")
    public Mono<Integer> getSingleData() {
        return Mono.just(1)
                .log();
    }
}
