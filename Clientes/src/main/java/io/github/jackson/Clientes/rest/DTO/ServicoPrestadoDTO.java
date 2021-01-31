package io.github.jackson.Clientes.rest.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*nessa classe ele cria o construtor sem parametro*/
@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String preco;
	
	@NotEmpty(message = "{campo.data.obrigatorio}")
	private String data;
	
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
}
