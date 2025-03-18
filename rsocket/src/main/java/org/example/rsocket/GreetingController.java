package org.example.rsocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class GreetingController {
    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(Mono<String> message, @DestinationVariable("name") String name) {
        return message.doOnNext(greeting -> System.out.println("Greeting: " + greeting))
                .map(g -> "Hello back to you, " + name + "!");
    }
}
