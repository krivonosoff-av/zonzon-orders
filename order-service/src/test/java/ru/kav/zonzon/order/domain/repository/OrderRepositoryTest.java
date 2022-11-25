package ru.kav.zonzon.order.domain.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.kav.zonzon.order.domain.model.Order;
import ru.kav.zonzon.order.domain.model.Person;
import ru.kav.zonzon.order.domain.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Tag("repository-test")
@ActiveProfiles("test")
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Product product;
    private Person person;

    @BeforeEach
    protected void init() {
        person = personRepository.save(Person.builder().name("test").build());
        product = productRepository.save(Product.builder().price(BigDecimal.valueOf(1)).name("test").build());
    }

    @Test
    public void createOrderWillSuccess() {
        var order = orderRepository.save(Order.builder()
                .person(person)
                .build());
        Assertions.assertEquals(1, orderRepository.count());
    }

    @Test
    public void createOrderWillFail() {
        orderRepository.save(Order.builder().person(person).build());
    }
}
