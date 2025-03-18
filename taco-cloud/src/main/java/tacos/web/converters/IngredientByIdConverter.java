package tacos.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.Ingredient;
import tacos.service.TacoService;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    @Autowired
    private TacoService tacoService;

    @Override
    public Ingredient convert(String id) {
        return tacoService.getById(id);
    }
}
