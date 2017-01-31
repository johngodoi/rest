package com.johngodoi.casadocodigo.rest.jaxrs.model.rest;

import com.johngodoi.casadocodigo.rest.jaxrs.model.Cerveja;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgodoi on 30/01/2017.
 */
@XmlRootElement
public class Cervejas {

    private List<Cerveja> cervejas = new ArrayList<>();

    @XmlElement(name="cerveja")
    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }

}