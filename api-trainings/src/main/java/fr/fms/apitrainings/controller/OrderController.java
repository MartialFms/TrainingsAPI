package fr.fms.apitrainings.controller;

import fr.fms.apitrainings.entities.OrderItem;
import fr.fms.apitrainings.entities.Orders;
import fr.fms.apitrainings.service.ImplOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private ImplOrderService implOrderService;

    @PostMapping("/order")
    @PostAuthorize("hasAuthority('ROLE_USER')")
    public Orders saveOrder(@RequestBody Orders orders) {
        return implOrderService.save(orders);
    }

    @GetMapping("/orders")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Orders> getAllOrders() {
        return implOrderService.getAll();
    }

    @GetMapping("/orderItems/{orderId}")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<OrderItem> getOrderItem(@PathVariable("orderId") long orderId) {
        return implOrderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/order/{orderId}")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public Orders getOneOrder(@PathVariable("orderId") long orderId) {
        return implOrderService.getOneById(orderId).get();
    }
}
