package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javahome.model.Produto;
import br.com.javahome.repository.produto.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery {

}
