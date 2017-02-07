package com.johngodoi.casadocodigo.rest.jaxrs.server.services;

import org.glassfish.jersey.jettison.JettisonFeature;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jgodoi on 31/01/2017.
 */
public class ApplicationJAXRS extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String,Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "com.johngodoi.casadocodigo.rest.jaxrs.server.services");
        return properties;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new JettisonFeature());
        return singletons;
    }
}
