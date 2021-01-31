package io.github.jackson.Clientes.rest;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.jackson.Clientes.model.entity.Cliente;
import io.github.jackson.Clientes.model.entity.ServicoPrestado;
import io.github.jackson.Clientes.model.repository.ClienteRepository;
import io.github.jackson.Clientes.model.repository.ServicoPrestadoRepository;
import io.github.jackson.Clientes.rest.DTO.ServicoPrestadoDTO;
import io.github.jackson.Clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

/**/
@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor

public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;
	

	/*cadastrar um serviço prestado DTO para pegar o id do cliente e colocar o serviço no cliente selecionado
	 * parseDate, para o padrão e formatação de data*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		/*vai selecionar o cliente por ID selecionar e depois salvar o serviços */
		Cliente cliente =
				clienteRepository
				.findById(idCliente)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe"));
		
		
		ServicoPrestado servicoPrestado  = new ServicoPrestado(); 
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		//valor do produto salvo em conversão utilziando a classe bigDecimal
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicoPrestado);
	}
	
	/*pesquisar no front a lista dos serviços prestados por nome e mes, required = false se não colocar ele não
	 * entra no metodo, defaultvaleu o valor vem vazio e retorna o valor findByNomeClienteAndMes não existe
	 * ele sera criado no repository*/
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = " ") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){
		
	return repository.findByNomeClienteAndMes("%" + nome +"%", mes);
	}

}

