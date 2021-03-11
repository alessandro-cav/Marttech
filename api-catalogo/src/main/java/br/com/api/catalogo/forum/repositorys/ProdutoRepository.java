package br.com.api.catalogo.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.catalogo.forum.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
