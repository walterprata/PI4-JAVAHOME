package br.com.javahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.pedido.ItensPedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer>{
	
	@Query(value="SELECT * FROM ITENS_PEDIDO WHERE PEDIDO_ID = ?", nativeQuery = true)
	public List<ItensPedido> buscarPedidosDoCliente(Integer id);

}
