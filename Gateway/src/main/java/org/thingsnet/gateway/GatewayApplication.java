package org.thingsnet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/v1/customer")
                        .uri("http://localhost:8081/api/v1/customer"))
                .route(p -> p
                        .path("/api/v1/device")
                        .uri("http://localhost:8082/api/v1/device"))
                .route(p -> p
                        .path("/api/v1/transport")
                        .uri("http://localhost:8083/api/v1/transport"))

                .build();
    }
}
