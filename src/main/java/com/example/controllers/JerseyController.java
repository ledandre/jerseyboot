package com.example.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Teste;

@Component
@Path("/jersey")
public class JerseyController {

    @Autowired Teste teste;

    @GET
    public Response sayJersey() {
        return Response.ok().entity(teste.sayMyName()).build();
    }
}