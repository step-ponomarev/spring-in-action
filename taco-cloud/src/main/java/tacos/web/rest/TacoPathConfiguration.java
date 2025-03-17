package tacos.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import tacos.order.TacoService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TacoPathConfiguration {
    private final TacoService tacoService;

    @Autowired
    public TacoPathConfiguration(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @Bean
    public RouterFunction<?> apiTacosRouterFunction() {
        return route(GET("/api/tacos/recent"), this::loadRecentTacos);
    }

    private Mono<ServerResponse> loadRecentTacos(ServerRequest serverRequest) {
        return ServerResponse.ok().body(tacoService.findRecentTacos(), Mono.class);
    }
}
