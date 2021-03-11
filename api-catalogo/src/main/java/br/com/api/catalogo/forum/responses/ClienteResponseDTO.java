package br.com.api.catalogo.forum.responses;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	
	private String nome;

	private String cpf;

	public static ClienteResponseDTO transformaObjetoEmDTO(Cliente cliente) {
		return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpf());
	}


}
