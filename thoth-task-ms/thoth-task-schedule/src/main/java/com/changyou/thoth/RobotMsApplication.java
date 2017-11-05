package com.changyou.thoth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by sunliangliang
 */

@EnableEurekaClient
@SpringBootApplication
public class RobotMsApplication{

    public static void main(String[] args) {
       SpringApplication.run(RobotMsApplication.class, args);
    }
}