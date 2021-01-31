package io.github.jackson.Clientes.exception;


public class UsuarioCadastradoException extends RuntimeException {

	public UsuarioCadastradoException(String login) {
		super("Usuario ja Cadastrado para o login  " +  login);
	}
}
