package io.github.jackson.Clientes.model.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jackson.Clientes.model.entity.ServicoPrestado;

/*integer representa o ID da entidade tipo do campo. */
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

	/*aqui vamos selecionar o que vamos pesquisar para retornar no servicoPrestadoDTO com a lista de serviços.
	 * fazendo o join com o cliente upper para tanto maiusculo como minusculo month faz a pesquisa por mes*/
	@Query("select s from  ServicoPrestado s join s.cliente c "
			+ "where upper(c.nome) like upper(:nome) and MONTH(s.data) =:mes  ")
	List<ServicoPrestado> findByNomeClienteAndMes(
			@Param("nome") String nome, @Param("mes") Integer mes);
}
