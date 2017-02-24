package com.johngodoi.casadocodigo.rest.client;

import com.johngodoi.casadocodigo.rest.client.model.rest.Cervejas;
import org.jboss.resteasy.client.jaxrs.ProxyBuilder;

import javax.ws.rs.client.ClientBuilder;

/**
 * Created by jgodoi on 23/02/2017.
 */
public class Cliente {
    public static void main(String[] args) {
        ProxyBuilder<CervejaService> proxy = ProxyBuilder.builder(CervejaService.class, ClientBuilder.newClient().target("http://localhost:8080/cervejaria/services"));
        CervejaService service = proxy.build();
        Cervejas cervejas = service.listeTodasAsCervejas(0);
        System.out.println(cervejas.getLinks().get(0));
    }
}
