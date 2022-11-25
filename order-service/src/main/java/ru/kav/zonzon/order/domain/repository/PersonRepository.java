package ru.kav.zonzon.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kav.zonzon.order.domain.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
