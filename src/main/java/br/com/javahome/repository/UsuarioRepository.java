package br.com.javahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value="SELECT * FROM USUARIO WHERE EMAIL = ?1", nativeQuery = true)
	public Usuario validaUsuario(String email);
	
	@Query(value="SELECT * FROM USUARIO WHERE STATUS = TRUE", nativeQuery = true)
	public List<Usuario> ListarUsuariosAtivos();
}
