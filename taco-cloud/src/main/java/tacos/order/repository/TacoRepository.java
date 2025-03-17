package tacos.order.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.order.data.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    List<Taco> findAllByOrderByCreatedAt(Pageable pageable);
}
