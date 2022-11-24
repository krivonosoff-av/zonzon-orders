package ru.kav.zonzon.order.domain.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCTS")
public class Product extends AbstractPersistable<Long> {

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Positive
    @Column(nullable = false)
    private BigDecimal price;
}
