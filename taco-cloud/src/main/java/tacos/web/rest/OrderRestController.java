package tacos.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tacos.order.TacoService;
import tacos.order.data.TacoOrder;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    @Autowired
    private TacoService tacoService;

    @PostMapping(consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TacoOrder> postOrder(@RequestBody Mono<TacoOrder> tacoOrder) {
        return tacoOrder.flatMap(tacoService::save);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public Mono<TacoOrder> putOrder(@PathVariable("id") Long id, @RequestBody Mono<TacoOrder> order) {
        return order.flatMap(o -> {
            o.setId(id);

            return tacoService.save(o);
        });
    }

    @PatchMapping(value = "/{id}", consumes = {"application/json"})
    public Mono<TacoOrder> patchOrder(@PathVariable("id") Long id, @RequestBody TacoOrder patch) {
        return tacoService.getOrder(id).flatMap(order -> {
            if (patch.getDeliveryCity() != null) {
                order.setDeliveryCity(patch.getDeliveryCity());
            }

            if (patch.getDeliveryStreet() != null) {
                order.setDeliveryStreet(patch.getDeliveryStreet());
            }

            if (patch.getDeliveryState() != null) {
                order.setDeliveryState(patch.getDeliveryState());
            }

            if (patch.getDeliveryZip() != null) {
                order.setDeliveryZip(patch.getDeliveryZip());
            }

            if (patch.getCcNumber() != null) {
                order.setCcNumber(patch.getCcNumber());
            }

            if (patch.getCcExpiration() != null) {
                order.setCcExpiration(patch.getCcExpiration());
            }

            if (patch.getCcCVV() != null) {
                order.setCcCVV(patch.getCcCVV());
            }

            return tacoService.save(order);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id) {
        tacoService.deleteOrder(id);
    }
}
