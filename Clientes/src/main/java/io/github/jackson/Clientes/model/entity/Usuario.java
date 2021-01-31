package io.github.jackson.Clientes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Cadastro para usuario e senha*/
@Entity
@Data
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*unique = true - o nome de usuario é unico no banco de dados o login vai ser o unico*/
	@Column(unique = true, name="login")
	@NotEmpty (message ="{campo.login.obrigatorio}")
	private String username;
	
	@Column(name = "senha")
	@NotEmpty(message ="{campo.senha.obrigatorio}")
	private String password;
}
