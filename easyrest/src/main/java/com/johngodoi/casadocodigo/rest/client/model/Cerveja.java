package com.johngodoi.casadocodigo.rest.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jgodoi on 30/01/2017.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cerveja {

    private String nome;
    private String descricao;
    private String cervejaria;

    private Tipo tipo;

    public Cerveja() {}

    public Cerveja(String nome, String descricao, String cervejaria, Tipo tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.cervejaria = cervejaria;
        this.tipo = tipo;
    }

    public void setCervejaria(String cervejaria) {
        this.cervejaria = cervejaria;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {
        LAGER, PILSEN, BOCK, WEIZEN;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return this.nome + " - " + this.descricao;
    }

}
