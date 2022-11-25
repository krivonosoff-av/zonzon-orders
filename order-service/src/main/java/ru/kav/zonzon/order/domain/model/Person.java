package ru.kav.zonzon.order.domain.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTS")
public class Person extends AbstractPersistable<Long> {

    private String name;
}
