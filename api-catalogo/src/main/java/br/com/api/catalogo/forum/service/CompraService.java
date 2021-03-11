package br.com.api.catalogo.forum.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.catalogo.forum.models.Cliente;
import br.com.api.catalogo.forum.models.Compra;
import br.com.api.catalogo.forum.models.Produto;
import br.com.api.catalogo.forum.repositorys.CompraRepository;
import br.com.api.catalogo.forum.requests.CompraRequestDTO;
import br.com.api.catalogo.forum.responses.ClienteResponseDTO;
import br.com.api.catalogo.forum.responses.CompraResponsesDTO;
import br.com.api.catalogo.forum.responses.ProdutoResponseDTO;
import br.com.api.catalogo.handler.ClienteNotFoundException;
import br.com.api.catalogo.handler.CompraNotFoundException;
import br.com.api.catalogo.handler.ProdutoNotFoundException;

@Service
public class CompraService {

	private ClienteService clienteService;

	private ProdutoService produtoService;

	private CompraRepository compraRepository;

	private double totalGeral;

	public CompraService(ClienteService clienteService, ProdutoService produtoService,
			CompraRepository compraRepository) {
		this.clienteService = clienteService;
		this.produtoService = produtoService;
		this.compraRepository = compraRepository;
	}

	public void adicionar(CompraRequestDTO compraRequestDTO) {

		try {
			Cliente cliente = this.clienteService.findByIdCliente(compraRequestDTO.getIdCliente());
			Produto produto = this.produtoService.findByIdProduto(compraRequestDTO.getIdProduto());
			double subTotal = compraRequestDTO.getQuantidade() * produto.getPreco();
			this.totalGeral = this.totalGeral + subTotal;
			Compra compra = CompraRequestDTO.transformaEmbjeto(compraRequestDTO, cliente, produto, subTotal,
					totalGeral);
			this.compraRepository.save(compra);

		} catch (ClienteNotFoundException e) {
			throw e;
		} catch (ProdutoNotFoundException e) {
			throw e;
		}
	}

	public void delete(Long id) {

		try {
			Compra compra = this.findByIdCompra(id);
			this.totalGeral = this.totalGeral - compra.getSubTtotal();
			this.compraRepository.delete(compra);
		} catch (CompraNotFoundException e) {
			throw e;
		}
	}

	private Compra findByIdCompra(Long id) {
		Optional<Compra> compra = this.compraRepository.findById(id);
		if (!compra.isPresent()) {
			throw new CompraNotFoundException("Codigo da compra não existe");
		}
		return compra.get();
	}

	public void atualizar(Long id, CompraRequestDTO compraRequestDTO) {

		try {
			Compra compra = this.findByIdCompra(id);
			Cliente cliente = this.clienteService.findByIdCliente(compraRequestDTO.getIdCliente());
			Produto produto = this.produtoService.findByIdProduto(compraRequestDTO.getIdProduto());
			double subTotal = compraRequestDTO.getQuantidade() * produto.getPreco();
			this.totalGeral = this.totalGeral + subTotal;
			compra.setCliente(cliente);
			compra.setProduto(produto);
			compra.setQuantidade(compraRequestDTO.getQuantidade());
			compra.setSubTtotal(subTotal);
			compra.setTotalGeral(totalGeral);

			this.compraRepository.saveAndFlush(compra);

		} catch (ClienteNotFoundException e) {
			throw e;
		} catch (ProdutoNotFoundException e) {
			throw e;
		} catch (CompraNotFoundException e) {
			throw e;
		}

	}

	public CompraResponsesDTO pedido(Long id) {
		CompraResponsesDTO compraResponsesDTO= null;
		try {
			Compra compra = this.findByIdCompra(id);
			ClienteResponseDTO clienteResponseDTO =ClienteResponseDTO.transformaObjetoEmDTO(compra.getCliente());
			ProdutoResponseDTO produtoResponseDTO = ProdutoResponseDTO.transformaObjetoEmDTO(compra.getProduto());
		 compraResponsesDTO = CompraResponsesDTO.transformaDTO(compra, clienteResponseDTO, produtoResponseDTO);
		} catch (CompraNotFoundException e) {
			throw e;
		}
		
		return compraResponsesDTO;
	}
}
