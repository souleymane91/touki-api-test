package org.jmg.gateway.travelpayoutsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TravelPayoutsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelPayoutsGatewayApplication.class, args);
    }

}
