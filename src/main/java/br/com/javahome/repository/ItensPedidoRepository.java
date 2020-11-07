package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.ItensPedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer>{

}
