package tacos.repository;

import org.springframework.data.repository.CrudRepository;

import tacos.data.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
