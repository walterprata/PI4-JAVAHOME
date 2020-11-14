package br.com.javahome.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.produto.ProdutoRepositoryQuery;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery {

	@Query(value="UPDATE produto SET quantidade = ?1 WHERE ID = ?2", nativeQuery = true)
	public void atualizarQuantidadeDeProduto(Integer quantidade, Integer id);
	
}
