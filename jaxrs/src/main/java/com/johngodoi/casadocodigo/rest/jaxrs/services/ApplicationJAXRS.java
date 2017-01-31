package com.johngodoi.casadocodigo.rest.jaxrs.services;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jgodoi on 31/01/2017.
 */
public class ApplicationJAXRS extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>>classes=new HashSet<>();
        classes.add(TestService.class);
        return classes;
    }
}
