package ru.kav.zonzon.order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kav.zonzon.order.domain.model.Order;
import ru.kav.zonzon.order.domain.model.Product;
import ru.kav.zonzon.order.domain.repository.OrderRepository;
import ru.kav.zonzon.order.domain.repository.PersonRepository;
import ru.kav.zonzon.order.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final PersonRepository personRepository;

    private final ProductRepository productRepository;

    @GetMapping("/orders")
    @ResponseBody
    public List<Order> orders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        order.setPerson(personRepository
                .findById(order.getPerson().getId())
                .orElseThrow(IllegalArgumentException::new));
        return orderRepository.save(order);
    }

    @PostMapping("/orders/{orderId}/addProduct/{productId}")
    @ResponseBody
    public Order addProduct(@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId) {
        var order = orderRepository.findById(orderId)
                .stream()
                .peek(o -> o.addProduct(productRepository.findById(productId)
                        .orElseThrow(IllegalArgumentException::new)))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        return orderRepository.save(order);
    }

    @PatchMapping("/orders/{orderId}")
    @ResponseBody
    public Order addProduct(@PathVariable("orderId") Long orderId, @RequestBody Product product) {
        var order = orderRepository.findById(orderId)
                .stream()
                .peek(o -> o.addProduct(productRepository.findById(product.getId())
                        .orElseThrow(IllegalArgumentException::new)))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        return orderRepository.save(order);
    }


}
