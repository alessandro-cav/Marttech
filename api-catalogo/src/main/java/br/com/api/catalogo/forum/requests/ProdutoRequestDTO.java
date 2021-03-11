package br.com.api.catalogo.forum.requests;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private String foto;

	private Double preco;

	public static Produto transformaDTOEmObjeto(ProdutoRequestDTO produtoRequestDTO) {
		return new Produto(produtoRequestDTO.getId(), produtoRequestDTO.getDescricao(), produtoRequestDTO.getFoto(), produtoRequestDTO.getPreco());
	}

}
