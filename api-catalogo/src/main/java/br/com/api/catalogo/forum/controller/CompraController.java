package br.com.api.catalogo.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.catalogo.forum.requests.CompraRequestDTO;
import br.com.api.catalogo.forum.responses.CompraResponsesDTO;
import br.com.api.catalogo.forum.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

	private CompraService compraService;

	public CompraController(CompraService compraService) {
		this.compraService = compraService;
	}

	@PostMapping
	public ResponseEntity<Void> adicionar(@RequestBody CompraRequestDTO compraRequestDTO) {
		compraService.adicionar(compraRequestDTO);
		return ResponseEntity.ok().build();
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		compraService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable(name = "id") Long id, @RequestBody CompraRequestDTO compraRequestDTO) {
		compraService.atualizar(id,compraRequestDTO);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<CompraResponsesDTO> pedido(@PathVariable(name = "id") Long id){
		CompraResponsesDTO CompraResponsesDTO = compraService.pedido(id);
		return ResponseEntity.ok(CompraResponsesDTO);
		
	}

}