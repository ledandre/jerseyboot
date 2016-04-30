package com.example.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.example.Person;

@Component
public class PersonBulkRepository implements PersonRepository {

    private EntityManager em;

    public PersonBulkRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("personPU");
        this.em = factory.createEntityManager();
    }

    @Override
    public void modify(List<Person> persons) {
        em.getTransaction().begin();

        persons.forEach(person -> {
            Query query = em.createQuery(generateQuery(person));
            query.executeUpdate();
        });

        em.getTransaction().commit();
        em.close();
    }

    private String generateQuery(Person person) {
        StringBuilder query = new StringBuilder("UPDATE Person p ");
        boolean firstField = true;

        if (person.getName() != null) {
            query.append("SET p.name='" + person.getName() + "' ");
            firstField = false;
        }

        if (person.getAge() != null) {
            if (firstField) {
                query.append("SET ");
            } else {
                query.append(", ");
            }
            query.append("p.age=" + person.getAge() + " ");
        }

        query.append(" WHERE p.id = " + person.getId());

        return query.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return em.createQuery("FROM " + Person.class.getSimpleName()).getResultList();
    }

    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person save(Person person) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();

        return person;
    }
}