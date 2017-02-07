package com.johngodoi.casadocodigo.rest.jaxrs.server.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by jgodoi on 31/01/2017.
 */
@Path("/olamundo")
public class TestService {
    @GET
    @Produces("text/plain")
    public String dizOla(){
        return "Olá, mundo REST!";
    }
}
