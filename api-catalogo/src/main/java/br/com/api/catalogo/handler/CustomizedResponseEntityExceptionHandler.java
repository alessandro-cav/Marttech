package br.com.api.catalogo.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> allHandlerException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> allHandlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<Object> allHandlerClienteNotFoundException(ClienteNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<Object> allHandlerProdutoNotFounException(ProdutoNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CompraNotFoundException.class)
	public ResponseEntity<Object> allHandlerCompraotFounException(CompraNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

}
