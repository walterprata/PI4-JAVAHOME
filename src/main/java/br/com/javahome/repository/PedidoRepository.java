package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
