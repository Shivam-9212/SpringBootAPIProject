package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Person {
    private final UUID id;

    @NotNull
    private String Name;

    public Person(@JsonProperty("id") UUID uuid, @JsonProperty("name") String Name){
        this.id = uuid;
        this.Name = Name;
    }
    public UUID getid() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

}
