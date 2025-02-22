package tacos.data;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.Data;

@Data
@UserDefinedType("taco")
public final class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
