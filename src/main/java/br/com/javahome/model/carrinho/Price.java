package br.com.javahome.model.carrinho;

import br.com.javahome.model.Cartao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Price {
    private Cartao cartao;
    List<BigDecimal> parcelas =  new ArrayList<>();
    private BigDecimal value;

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}