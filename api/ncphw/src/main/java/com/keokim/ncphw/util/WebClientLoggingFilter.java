package com.keokim.ncphw.util;//package com.keokim.ncphw.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.reactive.function.client.ClientResponse;
//import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
//import reactor.core.publisher.Mono;
//
//public class WebClientLoggingFilter {
//
//    private static final Logger log = LoggerFactory.getLogger(WebClientLoggingFilter.class);
//
//    public static ExchangeFilterFunction logRequest() {
//        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//            log.info("Request:\n{} {}", clientRequest.method(), clientRequest.url());
//            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
//            return Mono.just(clientRequest);
//        });
//    }
//
//    public static ExchangeFilterFunction logResponse() {
//        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            log.info("Response Header:\n{}", clientResponse.statusCode());
//            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
//
//            return logResponseBody(clientResponse);
////            return Mono.just(clientResponse);
//        });
//    }
//
//    private static Mono<ClientResponse> logResponseBody(ClientResponse clientResponse) {
//        ClientResponse.Builder builder = ClientResponse.from(clientResponse);
//        return clientResponse.bodyToMono(String.class).doOnNext(body -> log.info("Response Body:\n{}", body)).map(body -> {
//            builder.body(body);
//            return builder.build();
//        });
//    }
//}
