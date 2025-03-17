package tacos.web.rest.service;

import tacos.order.data.Ingredient;

public interface IngredientService {
    Iterable<Ingredient> findAll();

    Ingredient getIngredientById(String id);

    void updateIngredient(Ingredient ingredient);

    void deleteIngredient(String id);

    Ingredient postIngredient(Ingredient ingredient);
}
