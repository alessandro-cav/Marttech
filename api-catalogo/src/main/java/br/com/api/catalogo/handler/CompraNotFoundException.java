package br.com.api.catalogo.handler;

public class CompraNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CompraNotFoundException(String message) {
	
		super(message);
	}

}
