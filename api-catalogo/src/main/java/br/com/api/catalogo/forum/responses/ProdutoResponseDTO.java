package br.com.api.catalogo.forum.responses;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private String foto;

	private Double preco;

	public static ProdutoResponseDTO transformaObjetoEmDTO(Produto produto) {
		return new ProdutoResponseDTO(produto.getId(), produto.getDescricao(), produto.getFoto(), produto.getPreco());
	}

}
