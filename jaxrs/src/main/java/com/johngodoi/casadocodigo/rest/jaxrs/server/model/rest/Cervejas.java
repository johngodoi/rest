package com.johngodoi.casadocodigo.rest.jaxrs.server.model.rest;

import com.johngodoi.casadocodigo.rest.jaxrs.server.model.Cerveja;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgodoi on 30/01/2017.
 */
@XmlRootElement
public class Cervejas {

    private List<Cerveja> cervejas = new ArrayList<>();

    public Cervejas(List<Cerveja> cervejas){
        this.cervejas=cervejas;
    }

    public Cervejas(){}

    @XmlTransient
    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }

    @XmlElement(name="link")
    public List<Link> getLinks(){
        List<Link> links = new ArrayList<>();
        for (Cerveja cerveja : getCervejas()) {
            Link link = Link.fromPath("cervejas/{nome}")
                    .rel("cerveja")
                    .title(cerveja.getNome())
                    .build(cerveja.getNome());
            links.add(link);
        }
        return links;
    }
}