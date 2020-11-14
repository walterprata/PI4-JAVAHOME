package br.com.javahome.services;

import br.com.javahome.model.frete.Frete;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class EnviaCepService {
    private List<Frete> tipoEntregas = new ArrayList<>();

    public List<Frete> getEntregas() {
        Frete sedex = new Frete(1,"SEDEX",new BigDecimal("50"), "2");
        Frete sedexDex = new Frete(2,"SEDEX 10", new BigDecimal("62"), "1");
        Frete jadLog = new Frete(3,"JADLOG", new BigDecimal("25"), "5");
        Frete azul = new Frete(4,"AZUL", new BigDecimal("100.00"), "1");
        tipoEntregas.add(sedex);
        tipoEntregas.add(sedexDex);
        tipoEntregas.add(jadLog);
        tipoEntregas.add(azul);
        return tipoEntregas;
    }
}
