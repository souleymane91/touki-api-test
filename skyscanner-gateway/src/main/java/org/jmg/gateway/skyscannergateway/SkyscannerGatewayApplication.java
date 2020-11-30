package org.jmg.gateway.skyscannergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SkyscannerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyscannerGatewayApplication.class, args);
    }

}
