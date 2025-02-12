package tacos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tacos.data.Ingredient;
import tacos.data.Taco;
import tacos.data.TacoOrder;

@Service
public class TacoService {
    private static final List<Ingredient> INGREDIENTS = List.of(
            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            new Ingredient("CHD", "Cheddar", Ingredient.Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
    );

    public TacoOrder order() {
        return new TacoOrder();
    }

    public Taco taco() {
        return new Taco();
    }

    public List<Ingredient> getIngredients() {
        return INGREDIENTS;
    }

    public List<Ingredient> getIngredients(Ingredient.Type type) {
        return INGREDIENTS.stream()
                .filter(ingredient -> ingredient.getType() == type)
                .toList();
    }

    public Ingredient getById(String id) {
        return INGREDIENTS.stream()
                .filter(ingredient -> ingredient.getId().equals(id)).findFirst()
                .orElse(null);
    }
}
