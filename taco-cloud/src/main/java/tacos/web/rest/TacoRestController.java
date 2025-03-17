package tacos.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tacos.order.TacoService;
import tacos.order.data.Taco;

@RestController // разница с @Controller в том, что там пришлось бы использовать
                // @ResponseBody/ResponseEntity
@RequestMapping(value = "/api/tacos") // json by default
@CrossOrigin("http://tacocloud:8080")
public class TacoRestController {
    private final TacoService tacoService;

    @Autowired
    public TacoRestController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        final Taco taco = this.tacoService.findTacoById(id);

        return new ResponseEntity<>(taco, taco != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@Validated @RequestBody Mono<Taco> taco, Errors errors) {
        return taco.flatMap(tacoService::saveTaco);
    }
}
