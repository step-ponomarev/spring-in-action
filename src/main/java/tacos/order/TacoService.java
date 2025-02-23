package tacos.order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.order.data.Ingredient;
import tacos.order.data.Taco;
import tacos.order.data.TacoOrder;
import tacos.order.repository.IngredientRepository;
import tacos.order.repository.OrderRepository;

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
        return ingredientRepository.findByType(type);
    }

    public Ingredient getById(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
