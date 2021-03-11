package br.com.api.catalogo.forum.controller;

import java.util.List;

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

import br.com.api.catalogo.forum.models.Cliente;
import br.com.api.catalogo.forum.requests.ClienteRequestDTO;
import br.com.api.catalogo.forum.responses.ClienteResponseDTO;
import br.com.api.catalogo.forum.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody ClienteRequestDTO clienteRequestDTO) {
		Cliente cliente = this.clienteService.save(clienteRequestDTO);
		java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> findAll(){
		List<ClienteResponseDTO> clienteResponseDTO =  this.clienteService.findAll();
		return ResponseEntity.ok(clienteResponseDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> findById(@PathVariable(name = "id") Long id){
		ClienteResponseDTO clienteResponseDTO =  this.clienteService.findById(id);
		return ResponseEntity.ok(clienteResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
		 this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> update(@PathVariable(name = "id") Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
		ClienteResponseDTO empregadoResponseDTO =  this.clienteService.update(id, clienteRequestDTO);
		return ResponseEntity.ok(empregadoResponseDTO);
	}
	
	@GetMapping("/consultas")
	public ResponseEntity<List<ClienteResponseDTO>> consultas(Cliente filtro){
		ExampleMatcher exampleMatcher =  ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, exampleMatcher);
		List<ClienteResponseDTO>editoraResponseDTOs =  this.clienteService.findAll(example);
		return ResponseEntity.ok(editoraResponseDTOs);
	}
}
