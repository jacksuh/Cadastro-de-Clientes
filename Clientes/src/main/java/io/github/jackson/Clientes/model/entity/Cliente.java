package io.github.jackson.Clientes.model.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*classe cliente para mapear com o banco de dados, necessario instalar o lombok para adicionar os getts e setters automaticamente
 *dependencia baixada no POM  */

/*@DATA do lombok tanto cria getters e setters como cria parametros obrigatorios. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
	//fazer geracao automatica do ID no banco de dados, @id e @Generated
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//column para validar o campo e o tamanho obrigando a colocar o campo nullable
	/*o validador para cpf é @CPF o notNull campo não pode esta em branco
	 *  pega as informações de validação do arquivo criado messages.properties */
	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	/*o validador para cpf é @CPF o notNull campo não pode esta em branco
	 *  pega as informações de validação do arquivo criado messages.properties */
	@Column(nullable = false, length = 11)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	/*@JsonFormat formato de data para formatar da forma de dia mes e ano updatable= false nao vai atualizar a data. */
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	/*Prepersistir ele coloca a data de cadatro atual pega do computador*/
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
	
	
}
