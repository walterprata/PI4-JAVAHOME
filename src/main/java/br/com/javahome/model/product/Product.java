package br.com.javahome.model.product;

import br.com.javahome.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Product extends AbstractEntity<Integer> {
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String imgProduct;
    @Column(nullable = false)
    private String value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
