package tacos.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    public Mono<TacoOrder> save(TacoOrder order) {
        return orderRepository.save(order);
    }

    public Flux<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Flux<TacoOrder> getOrders(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(
                user,
                PageRequest.of(0, tacosProps.getOrderPageSize())
        );
    }

    @Nullable
    public Mono<TacoOrder> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Flux<Ingredient> getIngredients(Ingredient.Type type) {
        return ingredientRepository.findByType(type);
    }

    public Mono<Ingredient> getById(String id) {
        return ingredientRepository.findById(id);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public Mono<Taco> findRecentTacos() {
        return tacoRepository.findAllByOrderByCreatedAt(PageRequest.of(0, tacosProps.getTacoPageSize()));
    }

    public Mono<Taco> saveTaco(Taco taco) {
        return tacoRepository.save(taco);
    }

    @Nullable
    public Mono<Taco> findTacoById(Long id) {
        return tacoRepository.findById(id);
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
