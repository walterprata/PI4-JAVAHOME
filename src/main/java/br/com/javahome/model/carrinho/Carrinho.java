package br.com.javahome.model.carrinho;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrinho implements Serializable {
    private Map<ItenCarrinho,Integer> items = new LinkedHashMap<>();
    private LocalEntrega destino = new LocalEntrega();

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

    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(ItenCarrinho item : items.keySet()){
            total = total.add(getTotal(item));
        }
        return total;
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

    public LocalEntrega getDestino() {
        return destino;
    }

    public void setDestino(LocalEntrega destino) {
        this.destino = destino;
    }
}
