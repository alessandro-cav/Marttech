package br.com.api.catalogo.forum.requests;

import java.io.Serializable;

import br.com.api.catalogo.forum.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	
	private String nome;

	private String cpf;

	public static Cliente transformaDTOEmObjeto(ClienteRequestDTO clienteRequestDTO) {
		return new Cliente(clienteRequestDTO.getId(), clienteRequestDTO.getNome(), clienteRequestDTO.getCpf());
	}

}
