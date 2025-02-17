package tacos.repository;

import org.springframework.data.repository.CrudRepository;

import tacos.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
