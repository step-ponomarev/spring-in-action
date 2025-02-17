package tacos.repository;

import java.util.Optional;

import tacos.data.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Iterable<Ingredient> findByType(Ingredient.Type type);
    Ingredient save(Ingredient ingredient);
}
