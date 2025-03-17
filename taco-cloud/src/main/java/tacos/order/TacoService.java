package tacos.order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nullable;
import tacos.order.data.Ingredient;
import tacos.order.data.Taco;
import tacos.order.data.TacoOrder;
import tacos.order.repository.IngredientRepository;
import tacos.order.repository.OrderRepository;
import tacos.order.repository.TacoRepository;
import tacos.props.TacosProps;
import tacos.security.User;

@Service
public class TacoService {
    private final TacosProps tacosProps;

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TacoRepository tacoRepository;
    @Autowired
    private OrderRepository orderRepository;

    public TacoService(TacosProps tacosProps) {
        this.tacosProps = tacosProps;
    }

    public TacoOrder order() {
        return new TacoOrder();
    }

    public Taco taco() {
        return new Taco();
    }

    public TacoOrder save(TacoOrder order) {
        TacoOrder save = orderRepository.save(order);
        return save;
    }

    public List<Ingredient> getIngredients() {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<TacoOrder> getOrders(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(
                user,
                PageRequest.of(0, tacosProps.getOrderPageSize())
        );
    }

    @Nullable
    public TacoOrder getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Ingredient> getIngredients(Ingredient.Type type) {
        return ingredientRepository.findByType(type);
    }

    public Ingredient getById(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public List<Taco> findRecentTacos() {
        return tacoRepository.findAllByOrderByCreatedAt(PageRequest.of(0, tacosProps.getTacoPageSize()));
    }

    public Taco saveTaco(Taco taco) {
        return tacoRepository.save(taco);
    }

    @Nullable
    public Taco findTacoById(Long id) {
        return tacoRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        tacoRepository.deleteById(id);
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredientById(String id) {
        ingredientRepository.deleteById(id);
    }
}
