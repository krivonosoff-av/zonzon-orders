package ru.kav.zonzon.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kav.zonzon.order.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
