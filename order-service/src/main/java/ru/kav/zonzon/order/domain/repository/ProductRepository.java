package ru.kav.zonzon.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kav.zonzon.order.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
