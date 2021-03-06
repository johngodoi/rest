package com.johngodoi.casadocodigo.rest.client.model.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by john on 04/02/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomLink {

    @XmlAttribute
    private String href;

    @XmlAttribute
    private String rel;

    @XmlAttribute
    private String title;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
