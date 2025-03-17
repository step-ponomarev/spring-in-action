package tacos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.order.data.TacoOrder;
import tacos.order.TacoService;
import tacos.security.User;

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

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
//    @PostAuthorize() как PreAuthorize, только позволяет взять результат метода и сделать проверки,
//    например можно разрешить только админам или тем, кто создал заказ, чекая текущего юзера и рузльтат
    public String deleteAll() {
        tacoService.deleteAll();
        return "redirect:/";
    }

    public String ordersForCurrentUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders", tacoService.getOrders(user));

        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "tacoOrder") TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User currentUser) {
        if (errors.hasErrors()) {
            return orderForm();
        }

        order.setUser(currentUser);

        log.info("Processing order: ", order);
        tacoService.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
