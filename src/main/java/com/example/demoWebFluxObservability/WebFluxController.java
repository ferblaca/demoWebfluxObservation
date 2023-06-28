package com.example.demoWebFluxObservability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

/**
 * The type Decoupled controller.
 */
@RestController
@RequestMapping("observation")
public class WebFluxController {

    private static final Logger LOG = LoggerFactory.getLogger(WebFluxController.class);

    @PreAuthorize("hasRole('ROLE_5')")
    @GetMapping(path = "/unauthorized")
    public Mono<String> unauthorized() {
        return Mono.just("hello!");
    }

    @PreAuthorize("hasRole('ROLE_1')")
    @GetMapping(path = "/accessOK")
    public Mono<String> accessOK() {
        return Mono.just("hello!");
    }

    @GetMapping(path = "/error500")
    public Mono<Void> error500() {
        throw new HttpServerErrorException(HttpStatusCode.valueOf(500));
    }

}
