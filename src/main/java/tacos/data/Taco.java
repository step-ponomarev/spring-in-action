package tacos.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class Taco {
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name mus be at least 4 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(final Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
