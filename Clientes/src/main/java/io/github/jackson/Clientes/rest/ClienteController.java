package io.github.jackson.Clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.jackson.Clientes.model.entity.Cliente;
import io.github.jackson.Clientes.model.repository.ClienteRepository;


/*para a classe ser reconhecida como vai receber requisições a anotação é @restController
 * requestmapping - mapea qual a url base que vai ser tratada dentro desse controle, */
/* */
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

	/*conexão com o cliente repository precisa para salvar o cliente etc. */
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	/*Retorna lista de clientes findALL retorna todos os registros*/
	@GetMapping
	public List<Cliente> obterTodos(){
		return repository.findAll();
	}
	
	/*vai receber um cliente via json, vai passar o nome cpf data de cadastro é automatica, vamos
	 * acionar o repositorio usando o postmapping - mapea o metodo para uma requisicao com o post VIA URL /api/clientes
	 * indica que vai criar um recurso no servidor. ele retorna o repository e salva o cliente. */
	/*responseStatus - ele informa para o json que foi salvo com sucesso. CREATED
	 * @requestBody esse objeto vai ser o json */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	/*obter as informações de um cliente, get mapping atraves da URL e procurar por ID do cliente . */
	/*@pathVariable dessa forma da url lee pega do getmapping e disponibiliza preenche com o ID */
	/*findById retorna com um id e caso contrario ele vai para orElseThrow retorno de erro ou seja NOT_FOUND */
	@GetMapping("{id}")
	public Cliente acharPorId(@PathVariable Integer id) {
		return repository.findById(id).
				orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	/*RETORNO VOID SEM RETORNO, ele utiliza o deleteMapping para deletar passando o ID via URL
	 * caso encontre o cliente ele entra dento do MAP, retorna um objeto para a repository tabela cliente
	 * deletando o cliente e caso não encontre ele tambem retorna o OrElseThrow por isso foi colocado
	 * void no return.... httpstatus- no content é mensagem de sucesso sem nenhum retorno. */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id)
		.map(cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	/*retorno void não retorna tambem nada, feito atraves do PutMapping atualiza e envia novamente para o servidor.
	 * parametro sera pelo ID requestbody pega os dados do cliente no banco para depois fazemos a atualização 
	 * httpstatus- no content é mensagem de sucesso sem nenhum retorno., nesse caso o map manda para a repository sobre a
	 * atualização dados atualizados setnome setCPF caso de esse retorna o orElseThrow*/
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado ) {
		repository
		.findById(id)
		.map(cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCpf(clienteAtualizado.getCpf());
			 return repository.save(clienteAtualizado);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
	}
}
