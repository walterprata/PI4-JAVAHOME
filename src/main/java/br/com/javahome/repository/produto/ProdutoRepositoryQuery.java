package br.com.javahome.repository.produto;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {

	public Page<Produto> filtrar(ProdutoFilter lancamentoFilter, Pageable pageable);
}
