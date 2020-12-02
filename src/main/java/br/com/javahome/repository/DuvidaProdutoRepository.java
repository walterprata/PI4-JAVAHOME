package br.com.javahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javahome.model.produto.DuvidaProduto;

@Repository
public interface DuvidaProdutoRepository extends JpaRepository<DuvidaProduto, Integer>{
}
