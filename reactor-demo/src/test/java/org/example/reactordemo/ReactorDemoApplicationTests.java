package org.example.reactordemo;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@SpringBootTest
class ReactorDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testSimpleFlux() {
        final Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void testArrayFlux() {
        final String[] fruits = {"Apple", "Orange", "Grape", "Banana", "Strawberry"};
        final Flux<String> fruitFlux = Flux.fromArray(fruits);

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void testIntervalFlux() {
        final Flux<Long> intervalFLux = Flux.interval(Duration.ofSeconds(1)).take(5);

        StepVerifier.create(intervalFLux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    void testMergeFlux() {
        final Flux<String> characterFlux = Flux.just("Gargield", "Kojak", "Barbossa")
                .delayElements(Duration.ofMillis(500));

        Flux<String> foodFlux = Flux.just("Lasanga", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<String> merged = characterFlux.mergeWith(foodFlux);

        StepVerifier.create(merged)
                .expectNext("Gargield")
                .expectNext("Lasanga")
                .expectNext( "Kojak")
                .expectNext("Lollipops")
                .expectNext("Barbossa")
                .expectNext("Apples")
                .verifyComplete();
    }

    @Test
    void testZipFlux() {
        final Flux<String> characterFlux = Flux.just("Gargield", "Kojak", "Barbossa")
                .delayElements(Duration.ofMillis(500));

        Flux<String> foodFlux = Flux.just("Lasanga", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        final Flux<Tuple2<String, String>> zippedFlux = Flux.zip(characterFlux, foodFlux);

        StepVerifier.create(zippedFlux)
                .expectNextMatches(p -> p.getT1().equals("Gargield") && p.getT2().equals("Lasanga"))
                .expectNextMatches(p -> p.getT1().equals("Kojak") && p.getT2().equals("Lollipops"))
                .expectNextMatches(p -> p.getT1().equals("Barbossa") && p.getT2().equals("Apples"))
                .verifyComplete();
    }

    @Test
    void testFirstWithSignalFlux() {
        final Flux<String> characterFlux = Flux.just("Gargield")
                .delaySubscription(Duration.ofMillis(250));
        Flux<String> foodFlux = Flux.just("Lasanga");

        final Flux<String> first = Flux.firstWithSignal(characterFlux, foodFlux);
        StepVerifier.create(first)
                .expectNext("Lasanga")
                .verifyComplete();
    }

    @Test
    void testFilterFlux() {
        final Flux<String> characterFlux = Flux.just("tra", "Lasanga", "tra1", "trata", "nga").filter(s -> s.contains("tra"));

        StepVerifier.create(characterFlux)
                .expectNext("tra", "tra1", "trata")
                .verifyComplete();
    }

    // Вообще много операций, похожих на стримы

    @Test
    void testSkipFlux() {
        final Flux<String> characterFlux = Flux.just("Gargield", "Lasanga").skip(1);

        StepVerifier.create(characterFlux)
                .expectNext("Lasanga")
                .verifyComplete();
    }
}
