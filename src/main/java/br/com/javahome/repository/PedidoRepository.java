package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.usuario.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
		public java.util.List<Pedido> findByUsuario(Usuario usuario);
		public Pedido findFirstByUsuarioAndId(Usuario usuario,Integer id);
}
