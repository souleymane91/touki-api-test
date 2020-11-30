package org.jmg.gateway.obryansoftwaregateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private static final String BASE_URL = "https://compare-flight-prices.p.rapidapi.com";
    private static final String RAPID_API_KEY = "8075be410fmsh0047c7645e09e3ep189952jsn454a23e98453";
    private static final String RAPID_API_HOST = "compare-flight-prices.p.rapidapi.com";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // initialiser la recherche sur les vols entre deux ville (depart/destination)
                .route(r -> r
                        .path("/GetPricesAPI/StartFlightSearch.aspx**")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader("useQueryString", "true")
                                .addResponseHeader("Content-Type", "application/json")
                        )
                        .uri(BASE_URL)
                )

                // avoir les détails sur le prix des vol à partir d'un "searchID"
                .route(r -> r
                        .path("/GetPricesAPI/GetPrices.aspx**")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader("useQueryString", "true")
                                .addResponseHeader("Content-Type", "application/json")
                                .addResponseHeader("Content-Type", "application/json")
                        )
                        .uri(BASE_URL)
                )
                .build();
    }
}
