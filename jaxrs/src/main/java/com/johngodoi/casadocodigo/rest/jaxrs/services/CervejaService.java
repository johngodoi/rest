package com.johngodoi.casadocodigo.rest.jaxrs.services;

import com.johngodoi.casadocodigo.rest.jaxrs.model.Cerveja;
import com.johngodoi.casadocodigo.rest.jaxrs.model.CervejaJaExisteException;
import com.johngodoi.casadocodigo.rest.jaxrs.model.Estoque;
import com.johngodoi.casadocodigo.rest.jaxrs.model.rest.Cervejas;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by jgodoi on 31/01/2017.
 */
@Path("/cervejas")
@Consumes({MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_XML})
public class CervejaService {
    private static Estoque estoque = new Estoque();
    @GET
    public Cervejas listeTodasAsCervejas(){
        List<Cerveja> cervejas = (List<Cerveja>) estoque.listarCervejas();
        return new Cervejas(cervejas);
    }

    @GET
    @Path("{nome}")
    public Cerveja encontreCerveja(@PathParam("nome") String nomeDaCerveja){
        Cerveja cerveja = estoque.recuperarCervejaPeloNome(nomeDaCerveja);
        if(cerveja==null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return cerveja;
    }

    @POST
    public Response criarCerveja(Cerveja cerveja){
        try {
            estoque.adicionarCerveja(cerveja);
        } catch (CervejaJaExisteException e){
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        URI uri = UriBuilder.fromPath("cervejas/{nome}").build(cerveja.getNome());
        return Response.created(uri).entity(cerveja).build();
    }

    @PUT
    @Path("{nome}")
    public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja){
        encontreCerveja(nome);
        cerveja.setNome(nome);
        estoque.atualizarCerveja(cerveja);
    }

    @DELETE
    @Path("{nome}")
    public void apagarCerveja(@PathParam("nome") String nome){
        estoque.apagarCerveja(nome);
    }
}
