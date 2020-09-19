package br.com.javahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javahome.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
