package tacos.order.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.order.data.TacoOrder;
import tacos.security.User;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
