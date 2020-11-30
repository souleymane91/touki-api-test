package org.jmg.gateway.skyscannergateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private static final String BASE_URL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
    private static final String RAPID_API_KEY = "8075be410fmsh0047c7645e09e3ep189952jsn454a23e98453";
    private static final String RAPID_API_HOST = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // route qui permet d'avoir la liste des devises (currencies)
                .route(r -> r
                        .path("/api/v1/devises")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader("useQueryString", "true")
                                .rewritePath("/api/v1/devises", "/apiservices/reference/v1.0/currencies")
                        )
                        .uri(BASE_URL)
                )

                // avoir la liste des lieux (depart/destination)
                .route(r -> r
                        .path("/apiservices/autosuggest/v1.0/**")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader("useQueryString", "true")
                        )
                        .uri(BASE_URL)
                )
                .build();
    }
}
