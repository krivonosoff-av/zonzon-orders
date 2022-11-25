package ru.kav.zonzon.order;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.kav.zonzon.order.domain.model.Person;
import ru.kav.zonzon.order.domain.model.Product;
import ru.kav.zonzon.order.domain.repository.PersonRepository;
import ru.kav.zonzon.order.domain.repository.ProductRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    @Profile("dev")
    static class DataGenerator implements CommandLineRunner {

        private final ProductRepository productRepository;

        private final PersonRepository personRepository;

        @Override
        public void run(String... args) throws Exception {

            personRepository.saveAll(List.of(Person.builder().name("Andrei Krivonosov").build()));

            productRepository.saveAll(List.of(
                Product.builder().name("Большая бродилка").price(BigDecimal.valueOf(3500.50)).build(),
                Product.builder().name("Мачикоро").price(BigDecimal.valueOf(999.99)).build(),
                Product.builder().name("Манчкин").price(BigDecimal.valueOf(1999.00)).build()
            ));
        }
    }
}
