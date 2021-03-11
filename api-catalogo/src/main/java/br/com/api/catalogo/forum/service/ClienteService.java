package br.com.api.catalogo.forum.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.catalogo.forum.models.Cliente;
import br.com.api.catalogo.forum.repositorys.ClienteRepository;
import br.com.api.catalogo.forum.requests.ClienteRequestDTO;
import br.com.api.catalogo.forum.responses.ClienteResponseDTO;
import br.com.api.catalogo.handler.ClienteNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente findByIdCliente(Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			throw new ClienteNotFoundException("Cliente nao encontrada com esse numero " + id);
		}
		return cliente.get();
	}

	
	public Cliente save(ClienteRequestDTO clienteRequestDTO) {
		Cliente cliente = ClienteRequestDTO.transformaDTOEmObjeto(clienteRequestDTO);
		cliente = this.clienteRepository.save(cliente);
		return cliente;
	}

	public ClienteResponseDTO findById(Long id) {
	ClienteResponseDTO clienteResponseDTO;
		try {
			Cliente  cliente = this.findByIdCliente(id);
			 clienteResponseDTO = ClienteResponseDTO.transformaObjetoEmDTO(cliente);
		} catch (ClienteNotFoundException e) {
			throw e;
		}
		
		return clienteResponseDTO;
	}

	public List<ClienteResponseDTO> findAll() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		if (clientes.isEmpty()) {
			throw new ClienteNotFoundException("Lista de clientes está vazia!");
		}
		List<ClienteResponseDTO> clienteResponseDTO = clientes.stream()
				.map(e -> ClienteResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return clienteResponseDTO;
	}

	public void delete(Long id) {
		Cliente cliente = null;
		try {
		cliente = this.findByIdCliente(id);
			this.clienteRepository.delete(cliente);
		} catch (ClienteNotFoundException e) {
			throw e;
		}
	}

	public ClienteResponseDTO update(Long id, ClienteRequestDTO clineteRequestDTO) {
	ClienteResponseDTO clienteResponseDTO = null;
		try {
			Cliente cliente = this.findByIdCliente(id);
			cliente.setNome(clineteRequestDTO.getNome());
			cliente.setCpf(clineteRequestDTO.getCpf());
			cliente = this.clienteRepository.saveAndFlush(cliente);
			clienteResponseDTO = ClienteResponseDTO.transformaObjetoEmDTO(cliente);
		} catch (ClienteNotFoundException e) {
			throw e;
		}
		return clienteResponseDTO;
	}

	public List<ClienteResponseDTO> findAll(Example<Cliente> example) {
		List<Cliente> clientes = this.clienteRepository.findAll(example);
		if (clientes.isEmpty()) {
			throw new ClienteNotFoundException("Lista de clientes está vazia!");
		}
		List<ClienteResponseDTO> editoraResponseDTOs = clientes.stream()
				.map(e -> ClienteResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return editoraResponseDTOs;
	}
}
