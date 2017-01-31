package com.johngodoi.casadocodigo.rest.jaxrs.services;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jgodoi on 31/01/2017.
 */
public class ApplicationJAXRS extends Application {

    @Override
    public Map<String, Object> getProperties() {

        Map<String,Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "com.johngodoi.casadocodigo.rest.jaxrs.services");
        return properties;

    }
}
