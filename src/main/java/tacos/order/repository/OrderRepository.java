package tacos.order.repository;

import org.springframework.data.repository.CrudRepository;

import tacos.order.data.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
