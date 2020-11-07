package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javahome.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
