package com.johngodoi.casadocodigo.rest.jaxrs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jgodoi on 30/01/2017.
 */
public class Estoque {

    private Map<String, Cerveja> cervejas = new HashMap<>();


    public Estoque() {
        Cerveja primeiraCerveja = new Cerveja("Stella Artois",
                "A cerveja belga mais francesa do mundo :)",
                "Artois",
                Cerveja.Tipo.LAGER);
        Cerveja segundaCerveja = new Cerveja("Erdinger Weissbier",
                "Cerveja de trigo alemã",
                "Erdinger Weissbräu",
                Cerveja.Tipo.WEIZEN);
        this.cervejas.put("Stella Artois", primeiraCerveja);
        this.cervejas.put("Erdinger Weissbier", segundaCerveja);
    }


    public Collection<Cerveja> listarCervejas() {
        return new ArrayList<>(this.cervejas.values());
    }

    public void adicionarCerveja (Cerveja cerveja) {
        if(this.cervejas.containsKey(cerveja.getNome()))
            throw new CervejaJaExisteException();
        this.cervejas.put(cerveja.getNome(), cerveja);
    }

    public Cerveja recuperarCervejaPeloNome (String nome) {
        return this.cervejas.get(nome);
    }

    public void atualizarCerveja(Cerveja cerveja) {
        if(this.cervejas.containsKey(cerveja.getNome())){
            this.cervejas.put(cerveja.getNome(),cerveja);
        }
    }

    public void apagarCerveja(String nome){
        if(this.cervejas.containsKey(nome))
            this.cervejas.remove(nome);
    }
}
