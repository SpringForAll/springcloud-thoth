package com.prometheus.thoth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by sunliangliang
 */

@EnableEurekaClient
@SpringBootApplication
public class AIMsApplication{

    public static void main(String[] args) {
       SpringApplication.run(AIMsApplication.class, args);
    }
}