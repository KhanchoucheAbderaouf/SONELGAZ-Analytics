package com.pfe.msqueryreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsQueryReportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsQueryReportsApplication.class, args);
    }

}
