package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    public final PersonDao personDao;

    public PersonService (@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPeople(){
        return personDao.getAllPeople();
    }

    public int deletePersonByID(UUID id){
        return personDao.deletePersonByID(id);
    }

    public Optional<Person> selectPersonByID(UUID id){
        return personDao.selectPersonByID(id);
    }

    public int updatePersonByID(UUID id, String person){
        return personDao.updatePersonByID(id, person);
    }

    public int insertPerson(String name) { return personDao.insertPerson(name); }
}
