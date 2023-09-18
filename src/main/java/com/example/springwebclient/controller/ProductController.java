package com.example.springwebclient.controller;

import com.example.springwebclient.model.Product;
import com.example.springwebclient.services.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final WebClientController webClientController;
    private final WebClientService webClientService;
    @GetMapping
    public String viewHomePage() {
        return "index";
    }

//    @GetMapping("/movie")
//    public String viewAllMovie(final Model model) {
//        final Flux<Product> findAll = webClientService.findUsers();
//        final List<Product> productList = findAll.conllection().block(); // Convert Flux to List
//        model.addAttribute("products", productList);
//        return "page/movie";
//    }
@GetMapping("/movie")
public Mono<String> viewAllMovie(final Model model) {
    final Flux<Product> findAll = webClientService.findUsers();

    return findAll
            .delayElements(Duration.ofSeconds(1)) // Delay each element emission by 2 seconds
            .collectList()
            .flatMap(productList -> {
                model.addAttribute("products", productList);
                return Mono.just("page/movie");
            })
            .switchIfEmpty(Mono.just("page/movie")); // Provide a default value if Flux is empty
}

}
