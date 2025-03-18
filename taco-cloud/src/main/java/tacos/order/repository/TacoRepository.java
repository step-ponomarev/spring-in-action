package tacos.order.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import tacos.order.data.Taco;

@Repository
public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {
    Mono<Taco> findAllByOrderByCreatedAt(Pageable pageable);
}
