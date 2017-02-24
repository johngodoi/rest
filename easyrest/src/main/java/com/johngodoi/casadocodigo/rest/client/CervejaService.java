package com.johngodoi.casadocodigo.rest.client;

import com.johngodoi.casadocodigo.rest.client.model.Cerveja;
import com.johngodoi.casadocodigo.rest.client.model.rest.Cervejas;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jgodoi on 23/02/2017.
 */
@Path("/cervejas")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface CervejaService {

    @GET
    @Path("{nome}")
    public Cerveja encontreCerveja(@PathParam("nome") String nomeDaCerveja);

    @GET
    public Cervejas listeTodasAsCervejas(@QueryParam("pagina") int pagina);

    @POST
    public Response criarCerveja(Cerveja cerveja);

    @PUT
    @Path("{nome}")
    public void atualizarCerveja(@PathParam("nome") String nome);

    @DELETE
    @Path("{nome}")
    public void apagarCerveja(@PathParam("nome") String nome);

    @GET
    @Path("{nome}")
    @Produces("image/*")
    public Response recuperaImagem(@PathParam("nome") String nomeDaCerveja);

    @POST
    @Path("{nome}")
    @Consumes("image/*")
    public Response criaImagem(@PathParam("nome") String nomeDaImagem, @Context HttpServletRequest request, byte[] dados);
}
