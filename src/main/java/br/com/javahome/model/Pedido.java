package br.com.javahome.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.javahome.model.enums.TipoPagamento;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Double valorTotal;

	@OneToMany
    private List<Produto> produtos = new ArrayList();
	
	@ManyToOne
	private Usuario usuario;
	
	private String dataCompra;
	
	private TipoPagamento tipoPagamento;
	
	private String freteNome;
	
	private Double freteValor;
	
	private String fretePrazo;
	
	@OneToOne
	private Endereco endereco;
	
	private String statusCompra;

	public Pedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getFreteNome() {
		return freteNome;
	}

	public void setFreteNome(String freteNome) {
		this.freteNome = freteNome;
	}

	public Double getFreteValor() {
		return freteValor;
	}

	public void setFreteValor(Double freteValor) {
		this.freteValor = freteValor;
	}

	public String getFretePrazo() {
		return fretePrazo;
	}

	public void setFretePrazo(String fretePrazo) {
		this.fretePrazo = fretePrazo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getStatusCompra() {
		return statusCompra;
	}

	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	

	
}
