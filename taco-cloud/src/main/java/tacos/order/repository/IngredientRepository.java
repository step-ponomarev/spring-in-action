package tacos.order.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import tacos.order.data.Ingredient;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {
    Flux<Ingredient> findByType(Ingredient.Type type);
}
