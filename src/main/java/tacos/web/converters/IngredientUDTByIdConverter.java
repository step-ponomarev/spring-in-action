package tacos.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.Ingredient;
import tacos.data.IngredientUDT;
import tacos.service.TacoService;

@Component
public class IngredientUDTByIdConverter implements Converter<String, IngredientUDT> {
    @Autowired
    private TacoService tacoService;

    @Override
    public IngredientUDT convert(String id) {
        return convert(tacoService.getById(id));
    }

    private static IngredientUDT convert(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
