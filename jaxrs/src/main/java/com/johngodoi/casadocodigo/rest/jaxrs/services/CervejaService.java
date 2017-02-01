package com.johngodoi.casadocodigo.rest.jaxrs.services;

import com.johngodoi.casadocodigo.rest.jaxrs.model.Cerveja;
import com.johngodoi.casadocodigo.rest.jaxrs.model.CervejaJaExisteException;
import com.johngodoi.casadocodigo.rest.jaxrs.model.Estoque;
import com.johngodoi.casadocodigo.rest.jaxrs.model.rest.Cervejas;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jgodoi on 31/01/2017.
 */
@Path("/cervejas")
@Consumes({MediaType.TEXT_XML,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Produces({MediaType.TEXT_XML,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CervejaService {
    private static Map<String,String> EXTENSOES;
    private static final int TAMANHO_PAGINA = 20;
    private static Estoque estoque = new Estoque();

    static{
        EXTENSOES=new HashMap<>();
        EXTENSOES.put("image/jpg",".jpg");
    }

    @GET
    public Cervejas listeTodasAsCervejas(@QueryParam("pagina") int pagina){
        List<Cerveja> cervejas = (List<Cerveja>) estoque.listarCervejas(pagina, TAMANHO_PAGINA);
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

    @GET
    @Path("{nome}")
    @Produces("image/*")
    public Response recuperaImagem(@PathParam("nome") String nomeDaCerveja) throws IOException {
        InputStream is = CervejaService.class.getResourceAsStream("/"+nomeDaCerveja+".jpg");

        if(is==null) throw new WebApplicationException(Response.Status.NOT_FOUND);

        byte[] dados = new byte[is.available()];
        is.read(dados);
        is.close();
        return Response.ok(dados).type("image/jpg").build();
    }

    @POST
    @Path("{nome}")
    @Consumes("image/*")
    public Response criaImagem(@PathParam("nome") String nomeDaImagem, @Context HttpServletRequest request, byte[] dados) throws IOException, InterruptedException{
        String useHome = System.getProperty("user.home");
        String mimeType = request.getContentType();

        FileOutputStream fileOutputStream = new FileOutputStream(useHome + File.separator + nomeDaImagem + EXTENSOES.get(mimeType));
        fileOutputStream.write(dados);
        fileOutputStream.flush();
        fileOutputStream.close();

        return Response.ok().build();
    }
}
