package ru.kav.zonzon.order.domain.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order extends AbstractPersistable<Long> {

    @Builder
    public Order(Long clientId) {
        this.clientId = clientId;
    }

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @NotNull
    @Column(nullable = false)
    private Long clientId;

    @PositiveOrZero
    @Column(nullable = false)
    public BigDecimal total;

    @PrePersist
    protected void prePersist() {
        products = Collections.emptyList();
        total = BigDecimal.valueOf(0.00);
    }

    public void addProduct(Product product) {
        products.add(product);
        calculateTotal();
    }

    public void calculateTotal() {
        total = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
