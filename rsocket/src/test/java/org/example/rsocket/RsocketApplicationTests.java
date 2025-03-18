package org.example.rsocket;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootTest
class RsocketApplicationTests {
    @Autowired
    private RSocketRequester.Builder builder;

    @Test
    void contextLoads() {
    }

    @Test
    void testWebsocketRsocket() {
        final RSocketRequester websocket = builder.websocket(URI.create("ws://localhost:7000"));

        websocket.route("greeting/{name}", "stepan")
                .data("Hello!")
                .retrieveMono(String.class)
                .subscribe(r -> System.out.println(r));
    }
}
