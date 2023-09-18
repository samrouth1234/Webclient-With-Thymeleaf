package com.example.springwebclient.controller;

import com.example.springwebclient.model.Product;
import com.example.springwebclient.services.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web")
@RequiredArgsConstructor
public class WebClientController {

    private final WebClientService webClientService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Product> getUserById(@PathVariable String id) {
        return webClientService.findUserById(id);
    }

    @GetMapping(value = "/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Product> findAllUsers() {
        return webClientService.findUsers();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Product>> createUser(@RequestBody Product product) {
        return webClientService.createUser(product)
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(createdUser));
    }

    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> updateUser(@PathVariable String id, @RequestBody Product product) {
        return webClientService.updateUser(id, product);
    }

    @DeleteMapping("/product/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return webClientService.deleteUser(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
