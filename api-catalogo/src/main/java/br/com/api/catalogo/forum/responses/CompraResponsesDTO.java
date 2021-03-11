package br.com.api.catalogo.forum.responses;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Compra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponsesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ClienteResponseDTO cliente;

	private ProdutoResponseDTO produto;

	private Integer quantidade;

	private double subtotal;

	private double totalGeral;

	public static CompraResponsesDTO transformaDTO(Compra compra, ClienteResponseDTO clienteResponseDTO,
			ProdutoResponseDTO produtoResponseDTO) {
		return new CompraResponsesDTO(compra.getId(), clienteResponseDTO, produtoResponseDTO, compra.getQuantidade(),
				compra.getSubTtotal(), compra.getTotalGeral());
	}

}
