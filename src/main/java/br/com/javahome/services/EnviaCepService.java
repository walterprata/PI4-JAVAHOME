package br.com.javahome.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EnviaCepService {
    private HashMap<String, Double> tipoEntregas = new HashMap<>();

    public HashMap<String,Double> getEntregas(){
        tipoEntregas.put("SEDEX",50.00);
        tipoEntregas.put("SEDEX 10",62.00);
        tipoEntregas.put("JADLOG",25.55);
        tipoEntregas.put("AZUL",100.00);
        return tipoEntregas;
    }
}
