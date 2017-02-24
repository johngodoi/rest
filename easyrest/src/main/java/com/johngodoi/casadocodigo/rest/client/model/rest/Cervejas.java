package com.johngodoi.casadocodigo.rest.client.model.rest;

import com.johngodoi.casadocodigo.rest.client.Constants;
import com.johngodoi.casadocodigo.rest.client.model.Cerveja;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgodoi on 30/01/2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cervejas {

    @XmlElement(name = "link")
    private List<CustomLink> links;

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

    public List<Link> getLinks(){
        List<Link> links = new ArrayList<>();
        for (CustomLink customLink : this.links) {
            Link link = Link.fromUri(Constants.HOST+customLink.getHref())
                    .rel(customLink.getRel())
                    .title(customLink.getTitle()).build();
            links.add(link);
        }
        return links;
    }
}