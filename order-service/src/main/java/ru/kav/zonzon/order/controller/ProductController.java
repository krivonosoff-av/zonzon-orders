package ru.kav.zonzon.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kav.zonzon.order.domain.model.Product;
import ru.kav.zonzon.order.domain.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> products() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Optional<Product> products(@PathVariable Long productId) {
        return productRepository.findById(productId);
    }
}
