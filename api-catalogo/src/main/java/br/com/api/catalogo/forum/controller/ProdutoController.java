package br.com.api.catalogo.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import br.com.api.catalogo.forum.models.Produto;
import br.com.api.catalogo.forum.requests.ProdutoRequestDTO;
import br.com.api.catalogo.forum.responses.ProdutoResponseDTO;
import br.com.api.catalogo.forum.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private ProdutoService produtoService;


	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping
	public ResponseEntity<Produto> save (@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO){
		Produto produto =  this.produtoService.save(produtoRequestDTO);
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable(name = "id") Long id){
		ProdutoResponseDTO produtoResponseDTO = this.produtoService.findById(id);
		return ResponseEntity.ok(produtoResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
		List<ProdutoResponseDTO> livroResponseDTOs =  this.produtoService.findAll();
		return ResponseEntity.ok(livroResponseDTOs);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
		this.produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> update(@PathVariable(name = "id") Long id, @Valid @RequestBody ProdutoRequestDTO ProdutoRequestDTO ){
		ProdutoResponseDTO produtoResponseDTO = this.produtoService.update(id, ProdutoRequestDTO);
		return ResponseEntity.ok(produtoResponseDTO);
	}
	
	@GetMapping("/consultas")
	public ResponseEntity<List<ProdutoResponseDTO>> consultas(Produto filtro){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(filtro, exampleMatcher);
		List<ProdutoResponseDTO> produtoResponseDTOs =  this.produtoService.findAll(example);
		return ResponseEntity.ok(produtoResponseDTOs);
	}
}


