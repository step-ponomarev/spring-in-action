package tacos.order.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import tacos.order.data.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    Mono<Taco> findAllByOrderByCreatedAt(Pageable pageable);
}
