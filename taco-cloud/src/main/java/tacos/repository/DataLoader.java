package tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import tacos.data.Ingredient;

@Component
public class DataLoader {
    private final IngredientRepository repository;

    @Autowired
    public DataLoader(IngredientRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        repository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        repository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        repository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        repository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        repository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        repository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        repository.save(new Ingredient("CHD", "Cheddar", Ingredient.Type.CHEESE));
        repository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        repository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        repository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }
}
