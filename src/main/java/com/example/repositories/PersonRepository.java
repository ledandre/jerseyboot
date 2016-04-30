package com.example.repositories;

import java.util.List;

import com.example.Person;

public interface PersonRepository {

    public List<Person> findAll();

    public Person findById(Long id);
    
    public void modify(List<Person> persons);

    public Person save(Person person);
}