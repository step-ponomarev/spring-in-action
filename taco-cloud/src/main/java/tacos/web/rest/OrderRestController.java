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

import tacos.order.TacoService;
import tacos.order.data.TacoOrder;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    @Autowired
    private TacoService tacoService;

    @PostMapping(consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder tacoOrder) {
        return tacoService.save(tacoOrder);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public TacoOrder putOrder(@PathVariable("id") Long id, @RequestBody TacoOrder order) {
        order.setId(id);
        return tacoService.save(order);
    }

    @PatchMapping(value = "/{id}", consumes = {"application/json"})
    public TacoOrder patchOrder(@PathVariable("id") Long id, @RequestBody TacoOrder patch) {
        final TacoOrder order = tacoService.getOrder(id);
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
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id) {
        tacoService.deleteOrder(id);
    }
}
