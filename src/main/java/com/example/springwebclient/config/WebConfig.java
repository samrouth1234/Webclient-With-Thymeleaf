package com.example.springwebclient.config;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebConfig {
    @Bean("basic")
    public WebClient webClient() {
        final String baseUrl = "http://localhost:9292/api/v1";
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient;
    }

//    @Bean("Authorized")
//    public WebClient webClientAuthorized() {
//        return WebClient.builder()
//                .baseUrl("http://localhost:9292/api/v1")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .filter(this::sessionToken)
//                .build();
//    }
//
//    @Bean("OAuth2")
//    public WebClient webClientOAuth2(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
//        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
//                new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
//        oauth2Client.setDefaultClientRegistrationId("oauth2");
//        return WebClient.builder()
//                .baseUrl("http://localhost:9292/api/v1")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .filter(oauth2Client)
//                .build();
//    }

}
