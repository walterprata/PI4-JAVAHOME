package br.com.javahome.repository;



import org.springframework.data.jpa.repository.JpaRepository;



import br.com.javahome.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	
}
