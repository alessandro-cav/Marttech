package br.com.api.catalogo.forum.requests;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Cliente;
import br.com.api.catalogo.forum.models.Compra;
import br.com.api.catalogo.forum.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraRequestDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long idCliente;

	private Long idProduto;

	private Integer quantidade;

	public static Compra transformaEmbjeto(CompraRequestDTO compraRequestDTO, Cliente cliente, Produto produto,
			double subTotal, double totalGeral) {
		return new Compra(compraRequestDTO.getId(), cliente, produto, compraRequestDTO.getQuantidade(), subTotal, totalGeral);
	}

}
