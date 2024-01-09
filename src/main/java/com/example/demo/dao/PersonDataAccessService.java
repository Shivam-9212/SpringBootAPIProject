package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{
    private final JdbcTemplate jdbcTemplate;
    private UUID storedId;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(String name) {
        if(selectPersonByID(storedId).isPresent()){
            return 0;
        }
        else {
            final String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
            storedId = UUID.randomUUID();
            return jdbcTemplate.update(sql, storedId, name);
        }
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "SELECT * FROM person";
        List<Person> people = jdbcTemplate.query(sql, (resultSet,i)->{
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return  new Person(id, name);
        });
        return people;
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        final String sql = "SELECT * FROM person WHERE id = ?";

        try {
            Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
                UUID personId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                return new Person(personId, name);
            });
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException ex) {
            // If there's no result, return an empty Optional
            return Optional.empty();
        }
    }

    @Override
    public int deletePersonByID(UUID id) {
        final String sql = "DELETE FROM person WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonByID(UUID id, String name) {
        if (selectPersonByID(id).isPresent()) {
            System.out.println("Person found. Updating...");
            final String sql = "UPDATE person SET name = ? WHERE id = ?";
            int affectedRows = jdbcTemplate.update(sql, name, id);
            System.out.println("Rows affected: " + affectedRows);
            return affectedRows;
        } else {
            System.out.println("Person not found with ID: " + id);
            return 0; // Or another appropriate value indicating failure
        }
    }
}
