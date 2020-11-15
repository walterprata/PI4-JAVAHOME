package br.com.javahome.model.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.javahome.model.Endereco;
import br.com.javahome.model.usuario.Usuario;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private BigDecimal valorTotal;

	@OneToMany(cascade = CascadeType.ALL)
    private List<ItensPedido> itensPedido = new ArrayList<>();
	
	@ManyToOne
	private Usuario usuario;

	@Column(unique = true)
	private String numeroIdPedido;

	private String dataCompra;
	
	private String tipoPagamento;
	
	private String freteNome;
	
	private BigDecimal freteValor;
	
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
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

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
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

	public String getNumeroIdPedido() {
		return numeroIdPedido;
	}

	public void setNumeroIdPedido(String numeroIdPedido) {
		this.numeroIdPedido = numeroIdPedido;
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
