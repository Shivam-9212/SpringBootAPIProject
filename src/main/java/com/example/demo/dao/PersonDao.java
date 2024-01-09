package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(String name);
    List<Person> getAllPeople();
    Optional<Person> selectPersonByID(UUID id);
    int deletePersonByID(UUID id);
    int updatePersonByID(UUID id, String name);
}
