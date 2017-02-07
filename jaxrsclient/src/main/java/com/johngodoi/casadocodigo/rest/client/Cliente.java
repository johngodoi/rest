package com.johngodoi.casadocodigo.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by john on 04/02/17.
 */
public class Cliente {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
    }
}
