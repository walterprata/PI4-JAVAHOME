package br.com.javahome.model.frete;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Frete implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer freteId;

    private String freteNome;

    private BigDecimal freteValor = BigDecimal.ZERO;

    private String fretePrazo;

    public Frete(){}

    public Frete(Integer freteId,String freteNome, BigDecimal freteValor, String fretePrazo) {
        this.freteId = freteId;
        this.freteNome = freteNome;
        this.freteValor = freteValor;
        this.fretePrazo = fretePrazo;
    }

    public Integer getFreteId() {
        return freteId;
    }

    public void setFreteId(Integer freteId) {
        this.freteId = freteId;
    }

    public String getFreteNome() {
        return freteNome;
    }

    public void setFreteNome(String freteNome) {
        this.freteNome = freteNome;
    }

    public BigDecimal getFreteValor() {
        return freteValor;
    }

    public void setFreteValor(BigDecimal freteValor) {
        this.freteValor = freteValor;
    }

    public String getFretePrazo() {
        return fretePrazo;
    }

    public void setFretePrazo(String fretePrazo) {
        this.fretePrazo = fretePrazo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frete frete = (Frete) o;
        return Objects.equals(freteId, frete.freteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freteId);
    }
}
