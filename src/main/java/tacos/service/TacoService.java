package tacos.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.data.Ingredient;
import tacos.data.Taco;
import tacos.data.TacoOrder;
import tacos.repository.IngredientRepository;
import tacos.repository.OrderRepository;

@Service
public class TacoService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private OrderRepository orderRepository;

    public TacoOrder order() {
        return new TacoOrder();
    }

    public Taco taco() {
        return new Taco();
    }

    public TacoOrder save(TacoOrder order) {
        return orderRepository.save(order);
    }

    public List<Ingredient> getIngredients() {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Ingredient> getIngredients(Ingredient.Type type) {
        return StreamSupport.stream(ingredientRepository.findByType(type).spliterator(), false)
                .collect(Collectors.toList());
    }

    public Ingredient getById(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
