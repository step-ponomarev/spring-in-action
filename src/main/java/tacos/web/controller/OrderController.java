package tacos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.data.Taco;
import tacos.data.TacoOrder;
import tacos.service.TacoService;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final TacoService tacoService;

    @Autowired
    public OrderController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder order(@ModelAttribute(name = "tacoOrder") TacoOrder tacoOrder) {
        return tacoOrder;
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "tacoOrder") TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return orderForm();
        }

        log.info("Processing order: ", order);
        tacoService.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
