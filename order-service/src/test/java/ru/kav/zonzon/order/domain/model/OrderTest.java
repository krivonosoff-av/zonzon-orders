package ru.kav.zonzon.order.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OrderTest {

    private Person person;
    private Order order;
    private Product product1;
    private Product product2;

    @BeforeEach
    protected void init() {
        person = Person.builder().name("test").build();

        order = Order.builder().person(person).build();

        product1 = Product.builder()
                .name("test")
                .price(BigDecimal.valueOf(11.99))
                .build();

        product2 = Product.builder()
                .name("test")
                .price(BigDecimal.valueOf(0.01))
                .build();
    }

    @Test
    public void whenProductAddedToOrder_ThanTotalCalculated() {
        order.addProduct(product1);
        Assertions.assertEquals(BigDecimal.valueOf(11.99), order.getTotal());
    }

    @Test
    public void whenMultipleProductsAddedToOrder_ThanTotalCalculated() {
        order.addProduct(product1);
        order.addProduct(product2);
        Assertions.assertEquals(BigDecimal.valueOf(12.00).setScale(2), order.getTotal());
    }
}
