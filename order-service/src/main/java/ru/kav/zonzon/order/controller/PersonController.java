package ru.kav.zonzon.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kav.zonzon.order.domain.model.Person;
import ru.kav.zonzon.order.domain.repository.PersonRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> persons() {
        return personRepository.findAll();
    }
}
