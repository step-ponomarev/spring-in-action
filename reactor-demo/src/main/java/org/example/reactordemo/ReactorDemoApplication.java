package org.example.reactordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactorDemoApplication.class, args);
    }

}
