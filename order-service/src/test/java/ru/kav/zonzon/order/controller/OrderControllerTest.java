package ru.kav.zonzon.order.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import ru.kav.zonzon.order.domain.model.Order;
import ru.kav.zonzon.order.domain.model.Product;
import ru.kav.zonzon.order.domain.repository.OrderRepository;
import ru.kav.zonzon.order.domain.repository.ProductRepository;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    private final Supplier<URI> ordersUri = () -> URI.create("http://localhost:" + port + "/api/orders");

    private Order order;

    private List<Product> products;

    @BeforeEach
    protected void init() {
        order = restTemplate.postForObject(ordersUri.get(),
                Order.builder().clientId(1L).build(),
                Order.class);
        Assertions.assertNotNull(order);

        products = productRepository.findAll();
    }

    @Test
    public void createOrderWillSuccess() {
        Assertions.assertNotNull(order);
    }

    @Test
    public void getOrdersWillSuccess() {
        Order[] orders = restTemplate.getForObject(ordersUri.get(), Order[].class);
        Assertions.assertTrue(orders.length > 0);
    }

    @Test
    public void addProductToOrderWillSuccess() {
        Order response = restTemplate.postForObject(
                String.format(ordersUri.get() + "/%s/addProduct/%s", order.getId(), products.get(0).getId()),
                null,
                Order.class);
        Assertions.assertEquals(1, response.getProducts().size());
    }
}
