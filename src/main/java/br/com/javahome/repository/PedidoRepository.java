package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.usuario.Usuario;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
		public List<Pedido> findByUsuarioOrderByDataCompraDesc(Usuario usuario);
		public Pedido findFirstByUsuarioAndId(Usuario usuario,Integer id);
		public List<Pedido> findAllByOrderByDataCompraDesc();
}
