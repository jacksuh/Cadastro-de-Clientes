package io.github.jackson.Clientes.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.github.jackson.Clientes.rest.exception.ApiErrors;

import java.util.List;
import java.util.stream.Collectors;

/*para tratamento de erros ele intercepta os erros e manda a resposta*/
@RestControllerAdvice
public class ApplicationControllerAdvice {

	
	/*recebe e exception de validação por exemplo o @valid verifica que tem erro de validação é chamado de 
	 * MethodArgumentNotValidException quando ela ocorrer o controler vai capturar esse erro
	 * ExceptionHandler quando eu receber o method e coloco ele como argumento
	 * getBindingResult() resultado de todo a validação getALlErrors é uma array de erros.
	 * coloca em uma stream e o map vai pegar o objeto e transformar em outro e vai transformar ele em String
	 * vai retornar qual a mensagem que colocamos collect pega tudo que esta na string em lista 
	 * public ApiErros que seria a classe java para transformar a lista em json --- BAD_REQUEST retorna o  erro 400 
	 * se nao colocar nada o codigo vai ser sempre 200 ou seja o usuario vai pensar que esta tudo ok.
	 * isso é aplicado a toda a aplicação*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErros( MethodArgumentNotValidException ex ){
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors()
				.stream()
				.map(ObjectError -> ObjectError.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErrors(messages);
	}
	
	/*tratamento de erros para o response_status_exception da classe clientecontroller
	 *colocamos cliente não encontrado coletamos e o codigo de estatus
	 * getReason - ele não retorna o erro com o código 404 na frente
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") ira retornar essa mensagem */
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String mensagemErro = ex.getReason();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(mensagemErro);
		return new ResponseEntity(apiErrors, codigoStatus);
	}
}
