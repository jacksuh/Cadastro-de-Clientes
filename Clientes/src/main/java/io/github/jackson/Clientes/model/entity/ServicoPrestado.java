package io.github.jackson.Clientes.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/*mesmas anotações da classe cliente vai rodar os servicos */

@Entity
@Data
public class ServicoPrestado {
	
	/*geração automatica do ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*colocar a descrição obrigado o tamanho de 150 caracteres */
	@Column(nullable = false,length = 150)
	private String descricao;
	
	
	/*@ManyToOne anotação para integração entre classes que diz que existe Many (Muitos servicos) para um One um Cliente */
	/*JoinColumn define uma chave estrangeira para a classe serviço onde tera ligaçao com a tabela cliente. */
	@ManyToOne
	@JoinColumn(name = "id_Cliente")
	private Cliente cliente;
	
	/*valor do serviço prestado. */
	@Column
	private BigDecimal valor;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
}
