package com.netcracker.fapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.netcracker"})
@ComponentScan(basePackages = {"com.netcracker"})
public class FapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FapiApplication.class, args);
    }

}
