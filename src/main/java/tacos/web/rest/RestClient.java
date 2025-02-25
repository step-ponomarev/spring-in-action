package tacos.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tacos.order.data.Ingredient;

@Service
public class RestClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String id) {
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, id);
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(String id) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", id);
    }

    public Ingredient postIngredient(Ingredient ingredient) {
        return restTemplate.postForEntity("http://localhost:8080/ingredients", ingredient, Ingredient.class).getBody();
    }
}
