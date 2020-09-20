package br.com.javahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.javahome.model.DuvidaProduto;

public interface DuvidaProdutoRepository extends JpaRepository<DuvidaProduto, Integer>{

	@Query(value="SELECT * FROM DUVIDA_PRODUTO WHERE PRODUTO_ID = ?", nativeQuery = true)
	public List<DuvidaProduto> duvidaProduto(Integer id);
}
