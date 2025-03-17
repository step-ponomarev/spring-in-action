package tacos.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.order.TacoService;
import tacos.order.data.Ingredient;

@RestController
@RequestMapping(value = "/api/ingredient", produces = {"application/json"})
@CrossOrigin(origins = {"http://localhost:8080"})
public class IngredientRestController {
    private final TacoService tacoService;

    @Autowired
    public IngredientRestController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping
    public List<Ingredient> allIngredients() {
        return tacoService.getIngredients();
    }

    @PostMapping(consumes = {"application/json"})
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return tacoService.saveIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        tacoService.deleteIngredientById(id);
    }
}
