package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javahome.model.pedido.PedidoStatus;

public interface PedidoStatusRepository extends JpaRepository<PedidoStatus, Integer>{
	public PedidoStatus findByStatus(String pedidoStatus);
}
