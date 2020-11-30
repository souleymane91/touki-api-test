package org.jmg.gateway.travelpayoutsgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private static final String BASE_URL = "https://travelpayouts-travelpayouts-flight-data-v1.p.rapidapi.com";
    private static final String ACCESS_TOKEN = "e6be702594f324330fe4afed26e1c19e";
    private static final String RAPID_API_KEY = "8075be410fmsh0047c7645e09e3ep189952jsn454a23e98453";
    private static final String RAPID_API_HOST = "travelpayouts-travelpayouts-flight-data-v1.p.rapidapi.com";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // avoir la liste des pays
                .route(r -> r
                        .path("/api/v1/pays")
                        .filters(f -> f
                                .addRequestHeader("x-access-token", ACCESS_TOKEN)
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader(	"useQueryString", "true")
                                .rewritePath("/api/v1/pays", "/data/en-GB/countries.json")
                        )
                        .uri(BASE_URL)
                )

                // liste des ville
                .route(r -> r
                        .path("/api/v1/villes")
                        .filters(f -> f
                                .addRequestHeader("x-access-token", ACCESS_TOKEN)
                                .addRequestHeader("x-rapidapi-key", RAPID_API_KEY)
                                .addRequestHeader("x-rapidapi-host", RAPID_API_HOST)
                                .addRequestHeader(	"useQueryString", "true")
                                .rewritePath("/api/v1/villes", "/data/en-GB/cities.json")
                        )
                        .uri(BASE_URL)
                )
                .build();
    }
}
