package com.example.demo.dao;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(String name){
        DB.add(new Person(UUID.randomUUID(), name));
        return 1;
    }

    public List<Person> getAllPeople(){
        return DB;
    }

    public Optional<Person> selectPersonByID(UUID id){
        return DB.stream().filter(person -> person.getid().equals(id)).findFirst();
    }

    public int deletePersonByID(UUID id){
        if (!DB.remove(selectPersonByID(id))) {
            return 0;
        }
        return 1;
    }

    public int updatePersonByID(UUID id, String name){
        return selectPersonByID(id).map(p->{
            int indexOfPersonToUpdate = DB.indexOf(p);
            if(indexOfPersonToUpdate >= 0){
                DB.set(indexOfPersonToUpdate, new Person(id, name));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
