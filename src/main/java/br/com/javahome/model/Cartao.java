package br.com.javahome.model;

import java.util.Objects;

public class Cartao {
    private String nomeTitular;
    private String numeroCartao;
    private String cpfTitular;
    private String cvvCartao;
    private Integer indexParcela;

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public String getCvvCartao() {
        return cvvCartao;
    }

    public void setCvvCartao(String cvvCartao) {
        this.cvvCartao = cvvCartao;
    }

    public Integer getIndexParcela() {
        return indexParcela;
    }

    public void setIndexParcela(Integer indexParcela) {
        this.indexParcela = indexParcela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(numeroCartao, cartao.numeroCartao) &&
                Objects.equals(cpfTitular, cartao.cpfTitular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao, cpfTitular);
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "nomeTitular='" + nomeTitular + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", cpfTitular='" + cpfTitular + '\'' +
                ", csvCartao='" + cvvCartao + '\'' +
                ", indexParcela=" + indexParcela +
                '}';
    }
}
