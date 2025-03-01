package tacos.web.rest.service;

import java.util.Arrays;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import tacos.order.data.Ingredient;

public final class RestIngredientService implements IngredientService {
    private final RestTemplate restTemplate;

    public RestIngredientService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate.getInterceptors()
                    .add(createBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return Arrays.asList(
                restTemplate.getForObject("http://localhost:8080/ingredients/", Ingredient[].class)
        );
    }

    @Override
    public Ingredient getIngredientById(String id) {
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, id);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    @Override
    public void deleteIngredient(String id) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", id);
    }

    @Override
    public Ingredient postIngredient(Ingredient ingredient) {
        return restTemplate.postForEntity("http://localhost:8080/ingredients", ingredient, Ingredient.class).getBody();
    }

    private ClientHttpRequestInterceptor createBearerTokenInterceptor(String accessToken) {
        return (request, body, execution) -> {
            request.getHeaders().set("Authorization", "Bearer " + accessToken);
            return execution.execute(request, body);
        };
    }
}
