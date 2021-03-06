package br.com.javahome.model.carrinho;

import br.com.javahome.model.frete.Frete;
import br.com.javahome.model.pedido.Pedido;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<ItenCarrinho,Integer> items = new LinkedHashMap<>();
    private List<Frete> fretes = new ArrayList<>();
    private Frete freteSelecionado = new Frete();
    private Pedido novoPedido = new Pedido();

    public void zeroed(){
        items = new LinkedHashMap<>();
        fretes = new ArrayList<>();
        freteSelecionado = new Frete();
        novoPedido = new Pedido();
    }

    public void add(ItenCarrinho item) {
        items.put(item, getQuantidade(item) + 1);
    }

    public void removeQuantidade(ItenCarrinho item) {
        items.put(item, getQuantidade(item) - 1);
    }

    public Integer getQuantidade(ItenCarrinho item) {
        if (!items.containsKey(item)) {
            items.put(item, 0);
        }
        return items.get(item);
    }

    public Integer getQuantidade() {
        return items.values().stream().reduce(0, Integer::sum);
    }

    public Collection<ItenCarrinho> getItens() {
        return items.keySet();
    }

    public BigDecimal getSubTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(ItenCarrinho item : items.keySet()){
            total = total.add(getTotal(item));
        }
        return total;
    }

    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(ItenCarrinho item : items.keySet()){
            total = total.add(getTotal(item));
        }
        return total.add(freteSelecionado.getFreteValor());
    }

    public BigDecimal getTotal(ItenCarrinho item) {
        return item.getTotal(getQuantidade(item));
    }

    public void remove(ItenCarrinho shoppingItem) {
        items.remove(shoppingItem);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Frete> getFretes() {
        return fretes;
    }

    public void setFretes(List<Frete> fretes) {
        this.fretes = fretes;
    }

    public void clearFrestes(){
        fretes.clear();
    }

    public Frete getFreteSelecionado() {
        return freteSelecionado;
    }

    public void setFreteSelecionado(Frete freteSelecionado) {
        this.freteSelecionado = freteSelecionado;
    }

    public Pedido getNovoPedido() {
        return novoPedido;
    }

    public void setNovoPedido(Pedido novoPedido) {
        this.novoPedido = novoPedido;
    }
}
