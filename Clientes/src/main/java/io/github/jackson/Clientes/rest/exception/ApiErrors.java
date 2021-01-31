package io.github.jackson.Clientes.rest.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/*ele retorna uma lista de erros Strings para a classe ApplicationControllerAdvice*/ 
public class ApiErrors {

	
	@Getter
	private List<String> errors;
	
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}
}
