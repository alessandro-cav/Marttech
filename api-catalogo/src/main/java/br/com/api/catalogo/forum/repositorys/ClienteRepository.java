package br.com.api.catalogo.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.catalogo.forum.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
