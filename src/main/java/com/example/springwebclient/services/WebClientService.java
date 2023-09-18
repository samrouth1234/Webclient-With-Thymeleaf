package com.example.springwebclient.services;

import com.example.springwebclient.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final WebClient webClient;

    // Find all users
    public Flux<Product> findUsers(){
        return this.webClient
                .get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    // Find user by id
    public Mono<Product> findUserById(String id) {
        return this.webClient
                .get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // Create user
    public Mono<Product> createUser(Product user) {
        return webClient.post().uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // Update user by id
    public Mono<Product> updateUser(String id, Product user) {
        return this.webClient.put()
                .uri("/products/{id}", id) // Corrected URI pattern
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // Delete user by id
    public Mono<Void> deleteUser(String id) {
        return this.webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
