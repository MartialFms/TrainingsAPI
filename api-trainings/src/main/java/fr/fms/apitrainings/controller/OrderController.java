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
    public Orders saveOrder(@RequestBody Orders orders, @RequestHeader("Authorization") String authorization) {
        return implOrderService.save(orders);
    }

    @GetMapping("/orders")
    public List<Orders> getAllOrders(@RequestHeader("Authorization") String authorization) {
        return implOrderService.getAll();
    }

    @GetMapping("/orderItems/{orderId}")
    public List<OrderItem> getOrderItem(@PathVariable("orderId") long orderId, @RequestHeader("Authorization") String authorization) {
        return implOrderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/order/{orderId}")
    public Orders getOneOrder(@PathVariable("orderId") long orderId, @RequestHeader("Authorization") String authorization) {
        return implOrderService.getOneById(orderId).get();
    }
}
