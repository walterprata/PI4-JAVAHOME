package br.com.javahome.model.carrinho;

import br.com.javahome.services.EnviaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

@Component
public class LocalEntrega {
    @Autowired
    private EnviaCepService enviaCepService;

    private BigDecimal valor = BigDecimal.ZERO;

    public HashMap<String, Double> getCeps() {
        return enviaCepService.getEntregas();
    }

    public Collection<String> getLocais(){
        return enviaCepService.getEntregas().keySet();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
