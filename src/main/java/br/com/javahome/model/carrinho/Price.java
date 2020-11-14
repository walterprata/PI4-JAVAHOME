package br.com.javahome.model.carrinho;

import br.com.javahome.model.Cartao;

import java.math.BigDecimal;

public class Price {
    private Cartao cartao;
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

    @Override
    public String toString() {
        return "Price{" +
                "cartao=" + cartao +
                ", value=" + value +
                '}';
    }
}