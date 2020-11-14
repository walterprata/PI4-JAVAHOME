package br.com.javahome.model.carrinho;

import br.com.javahome.model.produto.Produto;

import java.math.BigDecimal;
import java.util.Objects;

public class ItenCarrinho {
    private Produto produto;
    private Integer productId;


//    public static itenCarrinho zeroed(){
//        Produto produto = new Produto();
//        Price price = new Price();
//        price.setBookType(combo);
//        price.setValue(BigDecimal.ZERO);
//        produto.setPrices(Arrays.asList(price));
//        return new ShoppingItem(product, combo);
//    }

    public ItenCarrinho(Produto produto) {
        this.produto = produto;
        this.productId = produto.getId();
    }

    public Double getPreco(){
       return produto.getValor();
    }

    public BigDecimal getTotal(Integer quantity) {
        return BigDecimal.valueOf(getPreco()).multiply(new BigDecimal(quantity));
    }

    public Integer getProductId() {
        return productId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItenCarrinho that = (ItenCarrinho) o;
        return Objects.equals(produto, that.produto) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, productId);
    }
}
