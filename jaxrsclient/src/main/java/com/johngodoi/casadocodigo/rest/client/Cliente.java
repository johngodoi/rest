package com.johngodoi.casadocodigo.rest.client;

import com.johngodoi.casadocodigo.rest.client.model.Cerveja;
import com.johngodoi.casadocodigo.rest.client.model.rest.Cervejas;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 04/02/17.
 */
public class Cliente {
    public static void main(String[] args) {
        List<Cerveja> cervejas = recuperarCervejas();
        for (Cerveja cerveja : cervejas) {
            System.out.println("Nome: " + cerveja.getNome());
        }

        Cerveja cerveja = new Cerveja();
        cerveja.setCervejaria("Ambev");
        cerveja.setNome("Skol");
        cerveja.setTipo(Cerveja.Tipo.PILSEN);

        Response response = ClientBuilder.newClient().target(Constants.HOST).path("cervejas").request().post(Entity.xml(cerveja));

        cerveja = criarCerveja(response);

        System.out.println(cerveja);

    }

    private static Cerveja criarCerveja(Response response) {
        if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
            Link link = Link.fromUri(response.getLocation()).build();
            Cerveja cerveja = ClientBuilder.newClient().invocation(link).accept(MediaType.APPLICATION_XML).get(Cerveja.class);
            return cerveja;
        }
        throw new RuntimeException("Código retornado diferente do esperado");
    }

    private static List<Cerveja> recuperarCervejas() {
        Client client = ClientBuilder.newClient();
        Cervejas cervejas = client.target(Constants.HOST).path("cervejas").request("application/xml").get(Cervejas.class);
        List<Cerveja> cervejaList = new ArrayList<>();
        for (Link link :
                cervejas.getLinks()) {
            Cerveja cerveja = ClientBuilder.newClient().invocation(link).accept(MediaType.APPLICATION_XML).get(Cerveja.class);
            cervejaList.add(cerveja);
        }
        return cervejaList;
    }
}
