package tacos.web.model;

import java.util.List;

import lombok.Data;
import tacos.data.Ingredient;

@Data
public class IngredientCategories {
    private final String title;
    private final List<Ingredient> ingredients;
}
