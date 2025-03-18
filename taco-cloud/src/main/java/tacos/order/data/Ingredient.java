package tacos.order.data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class Ingredient implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    private Long id;
    private @NonNull String slug;
    private @NonNull String name;
    private @NonNull Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
