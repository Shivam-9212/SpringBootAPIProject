package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    PersonService personService;

    @Autowired
    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/insert/{name}")
    int insertPerson(@PathVariable("name") String name) {
        return personService.insertPerson(name);
    }

    @GetMapping
    List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @DeleteMapping("/delete/{id}")
    int deletePersonByID(@PathVariable("id") UUID id) {
        return personService.deletePersonByID(id);
    }

    @GetMapping("/get/{id}")
    Optional<Person> selectPersonByID(@PathVariable("id") UUID id) {
        return personService.selectPersonByID(id);
    }

    @PutMapping("update/{id}")
    int updatePersonByID(@PathVariable("id") UUID id, @RequestParam String name) {
        return personService.updatePersonByID(id, name);
    }
}
