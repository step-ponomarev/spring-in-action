package tacos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findByType(Ingredient.Type type);
}
