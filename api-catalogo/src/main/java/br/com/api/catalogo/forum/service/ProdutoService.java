package br.com.api.catalogo.forum.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.catalogo.forum.models.Produto;
import br.com.api.catalogo.forum.repositorys.ProdutoRepository;
import br.com.api.catalogo.forum.requests.ProdutoRequestDTO;
import br.com.api.catalogo.forum.responses.ProdutoResponseDTO;
import br.com.api.catalogo.handler.ProdutoNotFoundException;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}


	public Produto findByIdProduto(Long id) {
		Optional<Produto> produto = this.produtoRepository.findById(id);
		if (!produto.isPresent()) {
			throw new ProdutoNotFoundException("Produto nao encontrada com esse numero " + id);
		}
		return produto.get();
	}

	
	public Produto save(ProdutoRequestDTO produtoRequestDTO) {
		Produto produto = ProdutoRequestDTO.transformaDTOEmObjeto(produtoRequestDTO);
		produto = this.produtoRepository.save(produto);
		return produto;
	}

	public ProdutoResponseDTO findById(Long id) {
	ProdutoResponseDTO produtoResponseDTO;
		try {
			Produto  produto = this.findByIdProduto(id);
			produtoResponseDTO = ProdutoResponseDTO.transformaObjetoEmDTO(produto);
		} catch (ProdutoNotFoundException e) {
			throw e;
		}
		
		return produtoResponseDTO;
	}

	public List<ProdutoResponseDTO> findAll() {
		List<Produto> produtos = this.produtoRepository.findAll();
		if (produtos.isEmpty()) {
			throw new ProdutoNotFoundException("Lista de produtos está vazia!");
		}
		List<ProdutoResponseDTO> produtoResponseDTOs = produtos.stream()
				.map(e -> ProdutoResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return produtoResponseDTOs;
	}

	public void delete(Long id) {
		Produto produto = null;
		try {
		produto = this.findByIdProduto(id);
			this.produtoRepository.delete(produto);
		} catch (ProdutoNotFoundException e) {
			throw e;
		}
	}

	public ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoRequestDTO) {
		ProdutoResponseDTO produtoResponseDTO = null;
		try {
			Produto produto = this.findByIdProduto(id);
			produto.setDescricao(produtoRequestDTO.getDescricao());
			produto.setFoto(produtoRequestDTO.getFoto());
			produto.setPreco(produtoRequestDTO.getPreco());
			produto = this.produtoRepository.saveAndFlush(produto);
			produtoResponseDTO = ProdutoResponseDTO.transformaObjetoEmDTO(produto);
		} catch (ProdutoNotFoundException e) {
			throw e;
		}
		return produtoResponseDTO;
	}

	public List<ProdutoResponseDTO> findAll(Example<Produto> example) {
		List<Produto> produtos = this.produtoRepository.findAll(example);
		if (produtos.isEmpty()) {
			throw new ProdutoNotFoundException("Lista de produtos está vazia!");
		}
		List<ProdutoResponseDTO> produtoResponseDTOs = produtos.stream()
				.map(e -> ProdutoResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return produtoResponseDTOs;
	}

}
