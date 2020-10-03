package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.javahome.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value="SELECT * FROM USUARIO WHERE EMAIL = ?1 AND SENHA = ?2", nativeQuery = true)
	public Usuario validaUsuario(String email, String senha);
}
