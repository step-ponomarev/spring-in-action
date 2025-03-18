package tacos.order.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import tacos.order.data.TacoOrder;
import tacos.security.User;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {
    Flux<TacoOrder> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
