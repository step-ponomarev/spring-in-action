package tacos.data;

import java.util.List;

import lombok.Data;

@Data
public final class Taco {
    private String name;
    private List<Ingredient> ingredients;
}
