package br.com.javahome.model.carrinho;

public enum FormaDePagamento {
    DEBITO,CREDITO;

    public String FormaDePagamento(FormaDePagamento formaDePagamento){
        if (formaDePagamento.equals(DEBITO)){
            return "debito";
        }else {
            return "credito";
        }
    }
}
