package br.com.javahome.model.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.javahome.Constantes;

@Entity
public class PedidoStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String status;
	
	public PedidoStatus() {
		this.status = Constantes.STATUS_AGUARDANDO_PAGAMENTO;
	}
	
	public PedidoStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
