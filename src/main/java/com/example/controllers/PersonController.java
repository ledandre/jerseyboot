package com.example.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PATCH;
import com.example.Person;
import com.example.repositories.PersonRepository;

@Component
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    private PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GET
    public Response list() {
        List<Person> persons = repository.findAll();
        return Response.ok().entity(persons).build();
    }

    @POST
    public Response create(Person person) {
        repository.save(person);
        try {
            return Response.created(new URI("/persons/" + person.getId())).build();

        } catch (URISyntaxException e) {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Person person = repository.findById(id);

        if (person == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok().entity(person).build();
    }

    @PATCH
    public Response modify(List<Person> persons) {
        repository.modify(persons);
        return Response.ok().build();
    }
}