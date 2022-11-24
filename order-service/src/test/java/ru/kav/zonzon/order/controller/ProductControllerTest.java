package ru.kav.zonzon.order.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import ru.kav.zonzon.order.domain.model.Order;
import ru.kav.zonzon.order.domain.model.Product;

import java.net.URI;
import java.util.function.Supplier;


@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final Supplier<URI> productsUri = () -> URI.create("http://localhost:" + port + "/api/products");

    private Product[] products;

    @BeforeEach
    protected void init() {
        products = restTemplate.getForObject(productsUri.get(), Product[].class);
    }

    @Test
    public void getProductsWillSuccess() {
        Assertions.assertNotNull(products);
    }
}
